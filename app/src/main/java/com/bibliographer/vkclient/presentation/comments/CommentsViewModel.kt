package com.bibliographer.vkclient.presentation.comments

import android.app.Application
import androidx.lifecycle.*
import com.bibliographer.vkclient.data.repository.NewsFeedRepositoryImpl
import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.domain.usecase.GetComments
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    application: Application,
    feedPost: FeedPost
) : ViewModel() {

    private val repository = NewsFeedRepositoryImpl(application)
    private val getCommentsUseCase = GetComments(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map { comments ->
            CommentsScreenState.Comments(
                comments = comments,
                feedPost = feedPost
            )
        }
}