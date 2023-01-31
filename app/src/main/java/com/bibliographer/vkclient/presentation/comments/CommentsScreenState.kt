package com.bibliographer.vkclient.presentation.comments

import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}
