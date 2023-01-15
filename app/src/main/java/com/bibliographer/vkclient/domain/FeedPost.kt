package com.bibliographer.vkclient.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.bibliographer.vkclient.R
import com.bibliographer.vkclient.navigation.Screen
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int = 0,
    val contentImageResId: Int = R.drawable.post_content_image,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = R.string.default_text_post.toString(),
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticsType.VIEWS, 121),
        StatisticItem(StatisticsType.SHARES, 12),
        StatisticItem(StatisticsType.LIKES, 2),
        StatisticItem(StatisticsType.COMMENTS, 25),
    )
): Parcelable {
    companion object {
         val NavigationType: NavType<FeedPost> = object: NavType<FeedPost>(false) {
             override fun get(bundle: Bundle, key: String): FeedPost? {
                 return bundle.getParcelable(key)
             }

             override fun parseValue(value: String): FeedPost {
                 return Gson().fromJson(value, FeedPost::class.java)
             }

             override fun put(bundle: Bundle, key: String, value: FeedPost) {
                 bundle.putParcelable(key, value)
             }

         }
    }
}
