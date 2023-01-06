package com.bibliographer.vkclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.StatisticItem
import com.bibliographer.vkclient.ui.theme.NavigationItem
import java.util.Collections.replaceAll

class MainViewModel: ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(times = 10) {
            add(FeedPost(id = it))
        }
    }

    private val _feedPosts = MutableLiveData<List<FeedPost>>(sourceList)
    val feedPosts : LiveData<List<FeedPost>> = _feedPosts

    fun updateCount(feedPost: FeedPost ,item: StatisticItem) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()

        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }

        val newFeedPosts = feedPost.copy(statistics = newStatistics)
        _feedPosts.value = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPosts.id) {
                    newFeedPosts
                } else {
                    it
                }
            }
        }
    }

    fun remove(feedPost: FeedPost) {
        val oldPosts = feedPosts.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _feedPosts.value = oldPosts
    }
}