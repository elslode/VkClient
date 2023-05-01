package com.bibliographer.vkclient.navigation

import android.net.Uri
import com.bibliographer.vkclient.domain.entity.FeedPost
import com.google.gson.Gson

sealed class Screen(
    val route: String
) {
    object NewsFeet : Screen(ROUTE_NEWS_FEED)
    object Favourite : Screen(ROUTE_FAVOURITE)
    object Profile : Screen(ROUTE_PROFILE)
    object Home : Screen(ROUTE_HOME)
    object Comments : Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"
        fun getRouteWithArgs(feedPost: FeedPost): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    companion object {

        const val KEY_FEED_POST = "feed_post"

        private const val ROUTE_HOME = "home"
        private const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        private const val ROUTE_NEWS_FEED = "news_feed"
        private const val ROUTE_FAVOURITE = "favourite"
        private const val ROUTE_PROFILE = "profile"
    }
}

fun String.encode(): String {
    return Uri.encode(this)
}

