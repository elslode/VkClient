package com.bibliographer.vkclient.di

import android.content.Context
import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.presentation.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance feedPost: FeedPost,
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}