package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.repository.NewsFeedRepository

class CheckAuthStateEvent(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        repository.checkAuthStateEvent()
    }
}