package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class LoadNextData @Inject constructor (
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}