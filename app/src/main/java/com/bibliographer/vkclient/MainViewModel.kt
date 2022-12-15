package com.bibliographer.vkclient

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _isFollowing = MutableLiveData<Boolean>(false)
    val isFollowed: LiveData<Boolean> = _isFollowing

    fun changeFollowingStatus() {
        val wasFollowing = _isFollowing.value ?: false
        _isFollowing.value = !wasFollowing
    }
}