package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.domain.entity.PostComment
import com.bibliographer.vkclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetComments(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return  repository.getComments(feedPost)
    }
}