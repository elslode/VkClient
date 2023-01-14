package com.bibliographer.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.bibliographer.vkclient.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeet.route,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeet.route) {
            newsFeedScreenContent()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST_ID) {
                    type = NavType.IntType
                },
                navArgument(Screen.KEY_FEED_POST_CONTENT) {
                    type = NavType.StringType
                }
            )
        ){
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            val feedPostContent = it.arguments?.getString(Screen.KEY_FEED_POST_CONTENT) ?: ""
            commentsScreenContent(FeedPost(
                id = feedPostId,
                contentText = feedPostContent
            ))
        }
    }
}