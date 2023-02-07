package com.bibliographer.vkclient.data.repository

import android.app.Application
import com.bibliographer.vkclient.data.mapper.NewsFeedMapper
import com.bibliographer.vkclient.data.network.ApiFactory
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.StatisticItem
import com.bibliographer.vkclient.domain.StatisticsType
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    private var nextFrom: String? = null

    suspend fun loadRecommendations(): List<FeedPost> {
        val startFrom = nextFrom
        if (startFrom == null && feedPosts.isNotEmpty()) return feedPosts

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
        return feedPosts
    }

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw java.lang.IllegalStateException("Token is null")
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
            removeIf { it.type == StatisticsType.LIKES }
            add(StatisticItem(StatisticsType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(
            statistics = newStatistics,
            isLiked = !feedPost.isLiked
        )
        val postIndex = _feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
    }
}