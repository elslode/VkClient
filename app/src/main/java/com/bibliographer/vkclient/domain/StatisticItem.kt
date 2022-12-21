package com.bibliographer.vkclient.domain

data class StatisticItem (
    val type: StatisticsType,
    val count: Int = 0
)

enum class StatisticsType {
    VIEWS, SHARES, COMMENTS, LIKES
}