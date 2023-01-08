package com.bibliographer.vkclient.domain

import com.bibliographer.vkclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.ic_avatar,
    val comment: String = "Long message text",
    val publicationData: String = "14:00"
)
