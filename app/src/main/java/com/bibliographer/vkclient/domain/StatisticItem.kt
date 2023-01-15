package com.bibliographer.vkclient.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatisticItem (
    val type: StatisticsType,
    val count: Int = 0
): Parcelable

enum class StatisticsType {
    VIEWS, SHARES, COMMENTS, LIKES
}