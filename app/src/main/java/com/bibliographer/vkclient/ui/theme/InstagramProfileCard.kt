package com.bibliographer.vkclient.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bibliographer.vkclient.MainViewModel
import com.bibliographer.vkclient.R
import java.sql.Statement

@Composable
fun InstagramProfileCard(
    viewModel: MainViewModel
) {

    val isFollowed = viewModel.isFollowed.observeAsState(false)

    Card(
        shape = RoundedCornerShape(
            topEnd = 4.dp,
            topStart = 4.dp
        ),
        border = BorderStroke(1.dp, MaterialTheme.colors.onBackground),
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.padding(8.dp)
    ) {
        Column {
            RowForBox()
            LogoApp()
            HashTagsLogo()
            LinkApp()
            ButtonFollow(isFollowed) {
                viewModel.changeFollowingStatus()
            }
        }
    }
}

@Composable
private fun RowForBox() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.instagram_svgrepo_com),
            contentDescription = "",
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .padding(8.dp)

        )
        StatisticsUser("Posts", "6,950")
        StatisticsUser("Followers", "436M")
        StatisticsUser("Following", "76")
    }
}

@Composable
private fun StatisticsUser(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier
            .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = value,
            fontSize = 14.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Black
        )

        Text(
            text = title,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Preview
@Composable
private fun LogoApp() {
    Box(modifier = Modifier.padding(4.dp)) {
        Text(
            text = "Instagram",
            fontSize = 36.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
        )
    }
}

@Composable
private fun ButtonFollow(
    isFollowed: State<Boolean>,
    clickListener:() -> Unit
) {

    Box(modifier = Modifier.padding(12.dp)) {
        Button(
            onClick = { clickListener() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isFollowed.value) {
                    MaterialTheme.colors.primary.copy(
                        alpha = 0.5f
                    )
                } else {
                    MaterialTheme.colors.primary
                }
            )
        ) {
            val text = if (isFollowed.value) {
                "Unfollow"
            } else {
                "Follow"
            }
            Text(text = text)
        }
    }
}

@Preview
@Composable
private fun HashTagsLogo() {
    Box(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "#YoutsToMake",
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Monospace,
        )
    }
}

@Preview
@Composable
private fun LinkApp() {
    Box(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "https://www.youtube.com/watch?v=kyN8UgXi6tw",
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
        )
    }
}