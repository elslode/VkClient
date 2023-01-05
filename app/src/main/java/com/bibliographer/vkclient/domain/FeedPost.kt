package com.bibliographer.vkclient.domain

import com.bibliographer.vkclient.R

data class FeedPost(
    val id: Int = 0,
    val contentImageResId: Int = R.drawable.post_content_image,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = R.string.default_text_post.toString(),
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticsType.VIEWS, 121),
        StatisticItem(StatisticsType.SHARES, 12),
        StatisticItem(StatisticsType.LIKES, 2),
        StatisticItem(StatisticsType.COMMENTS, 25),
    )
)
