package com.bibliographer.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class LikesDto(
    @SerializedName("count")
    val count: Int
)
