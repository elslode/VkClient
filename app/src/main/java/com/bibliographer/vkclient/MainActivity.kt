package com.bibliographer.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.bibliographer.vkclient.ui.theme.InstagramProfileCard
import com.bibliographer.vkclient.ui.theme.VkClientTheme

class MainActivity : ComponentActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Test(viewModel = viewModel)
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun Test(viewModel: MainViewModel) {
        VkClientTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
            ) {
                val models = viewModel.models.observeAsState(listOf())
                LazyVerticalGrid(cells = GridCells.Fixed(2)) {
                    items(models.value) { model ->
                        InstagramProfileCard(
                            model = model,
                            onFollowedButtonClickListener = {
                                viewModel.changeFollowingStatus(it)
                            }
                        )
                    }
                }
            }
        }
    }
}
