package com.bibliographer.vkclient.presentation.comments

import android.app.Application
import androidx.lifecycle.*
import com.bibliographer.vkclient.data.repository.NewsFeedRepository
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.PostComment
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CommentsViewModel(
    application: Application,
    feedPost: FeedPost
) : ViewModel() {

    private val repository = NewsFeedRepository(application)

    val screenState = repository.getComments(feedPost)
        .map { comments ->
            CommentsScreenState.Comments(
                comments = comments,
                feedPost = feedPost
            )
        }
}