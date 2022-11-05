package com.bibliographer.vkclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bibliographer.vkclient.ui.theme.VkClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserInfo(name =  "Елисей Вавулин", age =  24)
        }

    }
}

@Preview
@Composable
fun Greeting() {
    UserInfo(name = "Jhon", age = 121)
}

@Composable
fun UserInfo(name: String, age: Int) {
    Column {
        for(i in 0 until 10) {
            Text(text = "Hello, $name, you are $age age")
        }
    }
}
