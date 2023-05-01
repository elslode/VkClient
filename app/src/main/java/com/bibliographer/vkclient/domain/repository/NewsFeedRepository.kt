package com.bibliographer.vkclient.domain.repository

import com.bibliographer.vkclient.domain.entity.AuthState
import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>
    fun getRecommendations(): StateFlow<List<FeedPost>>
    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>
    suspend fun loadNextData()
    suspend fun checkAuthStateEvent()
    suspend fun deletePost(feedPost: FeedPost)
    suspend fun changeLikeStatus(feedPost: FeedPost)
}