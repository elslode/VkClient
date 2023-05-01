package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.repository.NewsFeedRepository

class LoadNextData(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}