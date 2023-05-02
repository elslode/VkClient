package com.bibliographer.vkclient.domain.usecase

import com.bibliographer.vkclient.domain.entity.AuthState
import com.bibliographer.vkclient.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAuthState @Inject constructor (
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return  repository.getAuthStateFlow()
    }
}