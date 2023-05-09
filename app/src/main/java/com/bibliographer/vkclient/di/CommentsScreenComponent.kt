package com.bibliographer.vkclient.di

import com.bibliographer.vkclient.domain.entity.FeedPost
import com.bibliographer.vkclient.presentation.ViewModelFactory
import com.bibliographer.vkclient.presentation.comments.CommentsViewModel
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        CommentsViewModelModule::class
    ]
)
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance feedPost: FeedPost
        ): CommentsScreenComponent
    }
}