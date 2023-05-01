package com.bibliographer.vkclient.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bibliographer.vkclient.data.repository.NewsFeedRepositoryImpl
import com.bibliographer.vkclient.domain.usecase.CheckAuthStateEvent
import com.bibliographer.vkclient.domain.usecase.GetAuthState
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(application)
    private val getAuthStateFlowUseCase = GetAuthState(repository)
    private val checkAuthStateEvent = CheckAuthStateEvent(repository)

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateEvent()
        }
    }
}