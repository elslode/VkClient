package com.bibliographer.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bibliographer.vkclient.ui.theme.ActivityResultTest
import com.bibliographer.vkclient.ui.theme.VkClientTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkClientTheme {
                ActivityResultTest()
            }
        }
    }
}

