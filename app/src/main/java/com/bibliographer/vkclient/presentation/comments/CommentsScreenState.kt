package com.bibliographer.vkclient.presentation.comments

import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.domain.entity.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}
