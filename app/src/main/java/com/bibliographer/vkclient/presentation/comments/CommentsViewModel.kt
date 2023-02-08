package com.bibliographer.vkclient.presentation.comments

import android.app.Application
import androidx.lifecycle.*
import com.bibliographer.vkclient.data.repository.NewsFeedRepository
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.PostComment
import kotlinx.coroutines.launch

class CommentsViewModel(
    application: Application,
    feedPost: FeedPost
): ViewModel() {

    private val repository = NewsFeedRepository(application)

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        viewModelScope.launch {
            val comments = repository.getComments(feedPost)
            _screenState.value = CommentsScreenState.Comments(
                comments = comments,
                feedPost = feedPost
            )
        }
    }
}