package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.domain.repository.NewsFeedRepository

class DeletePost(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        return repository.deletePost(feedPost)
    }
}