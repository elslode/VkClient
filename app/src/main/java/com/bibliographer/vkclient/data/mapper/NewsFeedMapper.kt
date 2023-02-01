package com.bibliographer.vkclient.data.mapper

import com.bibliographer.vkclient.data.model.NewsFeedResponseDto
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.StatisticItem
import com.bibliographer.vkclient.domain.StatisticsType
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDro: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()

        val posts = responseDro.newsFeedContent.posts
        val groups = responseDro.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                publicationDate = post.date.toString(),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.first()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticsType.LIKES, count = post.likes.count),
                    StatisticItem(type = StatisticsType.COMMENTS, count = post.comments.count),
                    StatisticItem(type = StatisticsType.SHARES, count = post.reposts.count),
                    StatisticItem(type = StatisticsType.VIEWS, count = post.views.count),
                )
            )
            result.add(feedPost)
        }
        return result
    }
}