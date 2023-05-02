package com.bibliographer.vkclient.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bibliographer.vkclient.domain.usecase.CheckAuthStateEvent
import com.bibliographer.vkclient.domain.usecase.GetAuthState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAuthStateFlowUseCase: GetAuthState,
    private val checkAuthStateEvent: CheckAuthStateEvent
) : ViewModel() {

    val authState = getAuthStateFlowUseCase()

    fun performAuthResult() {
        viewModelScope.launch {
            checkAuthStateEvent()
        }
    }
}