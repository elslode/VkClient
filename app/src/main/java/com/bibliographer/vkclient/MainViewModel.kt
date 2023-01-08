package com.bibliographer.vkclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bibliographer.vkclient.domain.FeedPost
import com.bibliographer.vkclient.domain.StatisticItem
import com.bibliographer.vkclient.ui.theme.HomeScreenState
import com.bibliographer.vkclient.ui.theme.NavigationItem

class MainViewModel: ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(times = 10) {
            add(FeedPost(id = it))
        }
    }

    private val initialState = HomeScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)
    val screenState  : LiveData<HomeScreenState> = _screenState

    fun updateCount(feedPost: FeedPost ,item: StatisticItem) {
        val oldPosts = screenState.value?.toMutableList() ?: mutableListOf()

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
        _screenState.value = oldPosts.apply {
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
        val oldPosts = screenState.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _screenState.value = oldPosts
    }
}