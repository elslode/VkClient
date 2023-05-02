package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.repository.NewsFeedRepository
import javax.inject.Inject

class CheckAuthStateEvent @Inject constructor (
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        repository.checkAuthStateEvent()
    }
}