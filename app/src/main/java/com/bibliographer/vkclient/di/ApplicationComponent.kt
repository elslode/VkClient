package com.bibliographer.vkclient.di

import android.content.Context
import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.presentation.ViewModelFactory
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

    fun getViewModelFactory(): ViewModelFactory
    fun getCommentsScreenComponentFactory(): CommentsScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}