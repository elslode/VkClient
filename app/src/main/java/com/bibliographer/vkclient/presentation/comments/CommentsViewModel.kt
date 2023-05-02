package com.bibliographer.vkclient.presentation.comments

import android.app.Application
import androidx.lifecycle.*
import com.bibliographer.vkclient.data.repository.NewsFeedRepositoryImpl
import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.domain.usecase.GetComments
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val feedPost: FeedPost,
    private val getCommentsUseCase: GetComments
) : ViewModel() {

    val screenState = getCommentsUseCase(feedPost)
        .map { comments ->
            CommentsScreenState.Comments(
                comments = comments,
                feedPost = feedPost
            )
        }
}