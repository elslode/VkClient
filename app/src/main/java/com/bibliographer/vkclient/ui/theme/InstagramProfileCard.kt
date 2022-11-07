package com.bibliographer.vkclient.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.bibliographer.vkclient.R

@Preview
@Composable
fun InstagramProfileCard() {
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
            ButtonFollow()
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
private fun LogoApp(){
    Box(modifier = Modifier.padding(4.dp)) {
        Text(
            text = "Instagram",
            fontSize = 36.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive,
        )
    }
}

@Preview
@Composable
private fun ButtonFollow(

){
    Box(modifier= Modifier.padding(12.dp)) {
        Button(onClick = {}) {
            Text(text = "Follow")
        }
    }
}

@Preview
@Composable
private fun HashTagsLogo(){
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
private fun LinkApp(){
    Box(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "https://www.youtube.com/watch?v=kyN8UgXi6tw",
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.Serif,
        )
    }
}

@Preview
@Composable
private fun CardProfileLight() {
    VkClientTheme(
        darkTheme = false
    ) {
        InstagramProfileCard()
    }
}

@Preview
@Composable
private fun CardProfileDark(
) {
    VkClientTheme(darkTheme = true) {
        InstagramProfileCard()
    }
}