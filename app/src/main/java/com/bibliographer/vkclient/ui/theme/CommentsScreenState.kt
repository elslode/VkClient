package com.bibliographer.vkclient.ui.theme

import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}
