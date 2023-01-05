package com.bibliographer.vkclient

import android.graphics.Color.alpha
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.bibliographer.vkclient.ui.theme.InstagramProfileCard
import com.bibliographer.vkclient.ui.theme.VkClientTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Test(viewModel = viewModel)
        }
    }

    @OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
    @Composable
    private fun Test(viewModel: MainViewModel) {
        VkClientTheme {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
            ) {
                val models = viewModel.models.observeAsState(listOf())
                val lazyListState = rememberLazyListState()
                val scope = rememberCoroutineScope()
                LazyColumn(
                    state = lazyListState
                ) {
                    items(models.value, key = { it.id }) { model ->
                        val dismissState = rememberDismissState()

                        if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                            viewModel.delete(model)
                        }
                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                Box(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxSize()
                                        .background(Color.Red.copy(alpha = 0.5f)),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text(
                                        modifier = Modifier.padding(16.dp),
                                        text = "Delete item",
                                        color = Color.White,
                                        fontSize = 24.sp
                                    )
                                }
                            }
                        ) {
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
}
