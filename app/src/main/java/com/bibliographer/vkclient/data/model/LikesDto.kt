package com.bibliographer.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class LikesDto(
    @SerializedName("likes") val count: Int,
    @SerializedName("user_likes") val userLikes: Int
)
