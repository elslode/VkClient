package com.bibliographer.vkclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bibliographer.vkclient.NewsFeedApplication
import com.bibliographer.vkclient.domain.entity.AuthState
import com.bibliographer.vkclient.getApplicationComponent
import com.bibliographer.vkclient.presentation.ViewModelFactory
import com.bibliographer.vkclient.ui.theme.VkClientTheme
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import javax.inject.Inject

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            val component = getApplicationComponent()
            val viewModel: MainViewModel = viewModel(factory = component.getViewModelFactory())
            val authState = viewModel.authState.collectAsState(AuthState.Initial)

            val launcher = rememberLauncherForActivityResult(
                contract = VK.getVKAuthActivityResultContract()
            ) {
                viewModel.performAuthResult()
            }

            VkClientTheme {
                when (authState.value) {
                    is AuthState.Authorized -> {
                        MainScreen()
                    }

                    is AuthState.NotAuthorized -> {
                        LoginScreen {
                            launcher.launch(listOf(VKScope.WALL, VKScope.FRIENDS))
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}

