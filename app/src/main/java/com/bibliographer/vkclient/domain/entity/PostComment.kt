package com.bibliographer.vkclient.domain.entity

data class PostComment(
    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val comment: String,
    val publicationData: String
)
