package com.bibliographer.vkclient

import android.app.Application
import com.bibliographer.vkclient.di.ApplicationComponent
import com.bibliographer.vkclient.di.DaggerApplicationComponent
import com.bibliographer.vkclient.domain.entity.FeedPost

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(
            FeedPost(
                0, 0, "", "", "", "", "", listOf(), false
            ), this
        )
    }
}