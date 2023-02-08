package com.bibliographer.vkclient.domain

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val comment: String,
    val publicationData: String
)
