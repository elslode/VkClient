package com.bibliographer.vkclient.ui.theme

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import java.time.Duration

@Composable
fun MainScreen() {
    val snackBarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val fabIsVisible = remember { mutableStateOf(true) }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        floatingActionButton = {
            if (fabIsVisible.value) {
                FloatingActionButton(onClick = {
                    scope.launch {
                        val action = snackBarHostState.showSnackbar(
                            message = "This is snackbar",
                            duration = SnackbarDuration.Long,
                            actionLabel = "Hide FAB"
                        )
                        if (action == SnackbarResult.ActionPerformed) {
                            fabIsVisible.value = false
                        }
                    }
                })
                {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = null
                    )
                }
            }

        },
        bottomBar = {
            BottomNavigation {
                val selectedItemPosition = remember { mutableStateOf(0) }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )

                items.forEachIndexed() { index, item ->
                    BottomNavigationItem(
                        selected = selectedItemPosition.value == index,
                        onClick = {
                            selectedItemPosition.value = index
                        },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(
                                text = stringResource(id = item.titleResId)
                            )
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }

            }
        }
    ) {

    }
}

