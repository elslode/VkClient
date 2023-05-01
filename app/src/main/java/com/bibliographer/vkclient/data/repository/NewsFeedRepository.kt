package com.bibliographer.vkclient.data.repository

import android.app.Application
import com.bibliographer.vkclient.data.mapper.NewsFeedMapper
import com.bibliographer.vkclient.data.network.ApiFactory
import com.bibliographer.vkclient.domain.AuthState
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.PostComment
import com.bibliographer.vkclient.domain.StatisticItem
import com.bibliographer.vkclient.domain.StatisticType
import com.bibliographer.vkclient.extensions.mergeWith
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token
        get() = VKAccessToken.restore(storage)

    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private val nextDataNeededEvent = MutableSharedFlow<Unit>(replay = 1)
    private val refresherListFlow = MutableSharedFlow<List<FeedPost>>()
    private val loadedListFlow = flow {
        nextDataNeededEvent.emit(Unit)
        nextDataNeededEvent.collect {
            val startFrom = nextFrom
            if (startFrom == null && feedPosts.isNotEmpty()) {
                emit(feedPosts)
                return@collect
            }

            val response = if (startFrom == null) {
                apiService.loadRecommendations(
                    token = getAccessToken()
                )
            } else {
                apiService.loadRecommendations(
                    token = getAccessToken(),
                    startFrom = startFrom
                )
            }

            nextFrom = response.newsFeedContent.nextFrom
            val posts = mapper.mapResponseToPosts(response)
            _feedPosts.addAll(posts)
            emit(feedPosts)
        }
    }.retry(2) {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private val _feedPosts = mutableListOf<FeedPost>()
    private val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFrom: String? = null

    private val checkAuthStateEvent = MutableSharedFlow<Unit>(replay = 1)

    val authStateFlow = flow {
        checkAuthStateEvent.emit(Unit)
        checkAuthStateEvent.collect {
            val currentToken = token
            val loggedIn = currentToken != null && currentToken.isValid
            val authState =   if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
            emit(authState)
        }
    }.stateIn(
        scope = coroutineScope,
        started = SharingStarted.Lazily,
        initialValue = AuthState.Initial
    )

    val recommendations: StateFlow<List<FeedPost>> =
        loadedListFlow
            .mergeWith(refresherListFlow)
            .stateIn(
                scope = coroutineScope,
                started = SharingStarted.Lazily,
                initialValue = feedPosts
            )

    suspend fun loadNextData() {
        nextDataNeededEvent.emit(Unit)
    }

    suspend fun checkAuthStateEvent() {
        checkAuthStateEvent.emit(Unit)
    }

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw java.lang.IllegalStateException("Token is null")
    }

    suspend fun deletePost(feedPost: FeedPost) {
        apiService.ignorePost(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        _feedPosts.remove(feedPost)
        refresherListFlow.emit(feedPosts)
    }

    fun getComments(feedPost: FeedPost): Flow<List<PostComment>> = flow {
        val comments = apiService.getComments(
            token = getAccessToken(),
            ownerId = feedPost.communityId,
            postId = feedPost.id
        )
        val commentsDomain = mapper.mapResponseToComments(comments)
        emit(commentsDomain)
    }.retry(2) {
        delay(RETRY_TIMEOUT_MILLIS)
        true
    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id,
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id,
            )
        }

        val newLikesCount = response.likes.count
        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(StatisticType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(
            statistics = newStatistics,
            isLiked = !feedPost.isLiked
        )
        val postIndex = _feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
        refresherListFlow.emit(feedPosts)
    }

    companion object {
        private const val RETRY_TIMEOUT_MILLIS = 3000L
    }
}