package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.domain.repository.NewsFeedRepository

class ChangeLikeStatus(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}