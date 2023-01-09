package com.bibliographer.vkclient.ui.theme

import com.bibliographer.vkclient.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial: NewsFeedScreenState()
    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}
