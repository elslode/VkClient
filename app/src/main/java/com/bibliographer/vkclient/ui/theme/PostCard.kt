package com.bibliographer.vkclient.ui.theme

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bibliographer.vkclient.R

@Preview
@Composable
fun PostCard() {
    Card {
        Column(
            modifier = Modifier.padding(8 .dp)
        ) {
            HeaderPost()
            Spacer(modifier = Modifier.padding(8.dp))
            ContentPost()
            ReactionUserBlockPost()
        }
    }
}

@Preview
@Composable
private fun HeaderPost() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(
                id = R.drawable.post_comunity_thumbnail
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "/dev/null",
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "12:12",
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

@Preview
@Composable
private fun ContentPost() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            color = MaterialTheme.colors.onPrimary,
            text = stringResource(R.string.default_text_post)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.post_comunity_thumbnail),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Preview
@Composable
private fun ReactionUserBlockPost() {
    Row(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ItemReactionUsers(image = R.drawable.ic_views_count, text = "12112")
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            ) {
            ItemReactionUsers(image = R.drawable.ic_share, text = "121")
            ItemReactionUsers(image = R.drawable.ic_comment, text = "9")
            ItemReactionUsers(image = R.drawable.ic_like, text = "1221")
        }
    }
}

@Composable
private fun ItemReactionUsers(image: Int, text: String) {
    Row {
        Icon(
            painter = painterResource(image),
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
