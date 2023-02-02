package com.bibliographer.vkclient.presentation.news

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.bibliographer.vkclient.R
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.StatisticItem
import com.bibliographer.vkclient.domain.StatisticsType
import com.bibliographer.vkclient.ui.theme.DarkRed

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onLikeClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onViewClickListener: (StatisticItem) -> Unit
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            HeaderPost(feedPost)
            Spacer(modifier = Modifier.padding(8.dp))
            ContentPost(feedPost)
            Statistics(
                statistics = feedPost.statistics,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = onLikeClickListener,
                onShareClickListener = onShareClickListener,
                onViewClickListener = onViewClickListener,
                isFavourite = feedPost.isFavourite
            )
        }
    }
}

@Composable
private fun HeaderPost(feedPostCard: FeedPost) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = feedPostCard.communityImageUrl,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = feedPostCard.communityName,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = feedPostCard.publicationDate,
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun ContentPost(feedPostCard: FeedPost) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            color = MaterialTheme.colors.onPrimary,
            text = feedPostCard.contentText
        )
        Spacer(modifier = Modifier.padding(8.dp))
        AsyncImage(
            model = feedPostCard.communityImageUrl,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikeClickListener: (StatisticItem) -> Unit,
    onCommentClickListener: (StatisticItem) -> Unit,
    onShareClickListener: (StatisticItem) -> Unit,
    onViewClickListener: (StatisticItem) -> Unit,
    isFavourite: Boolean
) {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val viewsItem = statistics
                .getItemByType(StatisticsType.VIEWS)
            ItemReactionUsers(
                image = R.drawable.ic_views_count,
                text = formatStatisticItem(viewsItem.count),
                onIconReactionClickListener = {
                    onViewClickListener(viewsItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val shareItem = statistics.getItemByType(StatisticsType.SHARES)
            ItemReactionUsers(
                image = R.drawable.ic_share,
                text = formatStatisticItem(shareItem.count),
                onIconReactionClickListener = {
                    onShareClickListener(shareItem)
                }
            )

            val commentItem = statistics.getItemByType(StatisticsType.COMMENTS)
            ItemReactionUsers(
                image = R.drawable.ic_comment,
                text = formatStatisticItem(commentItem.count),
                onIconReactionClickListener = {
                    onCommentClickListener(commentItem)
                }
            )

            val likeItem = statistics.getItemByType(StatisticsType.LIKES)
            ItemReactionUsers(
                image = if (isFavourite) R.drawable.ic_like_set else R.drawable.ic_like,
                text = formatStatisticItem(likeItem.count),
                onIconReactionClickListener = {
                    onLikeClickListener(likeItem)
                },
                tint = if (isFavourite) DarkRed else MaterialTheme.colors.onSecondary
            )
        }
    }
}

private fun formatStatisticItem(count: Int): String {
    return if (count > 100_000) {
        String.format("%sK", (count/1000))
    } else if (count > 1000) {
        String.format("%.1fK", (count/1000f))
    } else {
        count.toString()
    }
}

private fun List<StatisticItem>.getItemByType(type: StatisticsType): StatisticItem {
    return this.find { it.type == type } ?: throw java.lang.IllegalStateException()
}

@Composable
private fun ItemReactionUsers(
    image: Int,
    text: String,
    onIconReactionClickListener: () -> Unit,
    tint: Color = MaterialTheme.colors.onSecondary
) {
    Row(
        modifier = Modifier.clickable { onIconReactionClickListener() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(image),
            contentDescription = null,
            tint = tint
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
