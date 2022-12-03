package com.bibliographer.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bibliographer.vkclient.ui.theme.PostCard
import com.bibliographer.vkclient.ui.theme.VkClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkClientTheme() {
                Box(
                    modifier =
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                        .padding(8.dp)
                ) {
                    Test2()
                }
            }
        }
    }

    @Composable
    private fun VkPostCard() = PostCard()

    @Preview
    @Composable
    private fun Test2() {
        val selectedItem by remember { mutableStateOf(0) }
        val items = listOf("Songs", "Artist", "Playlist")

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "TopAppBarTitile")
                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Filled.Menu, contentDescription = null)
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigation {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            selected = selectedItem == index,
                            onClick = { /*TODO*/ },
                            label = { Text(item) },
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            },
            drawerContent = {
                Text(text = "First text")
                Text(text = "Second text")
                Text(text = "Thirthd text")
            }
        ) {
            Text(
                modifier = Modifier.padding(20.dp),
                text = "This is scaffold content"
            )
        }
    }
}

