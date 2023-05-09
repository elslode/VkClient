package com.bibliographer.vkclient.di

import androidx.lifecycle.ViewModel
import com.bibliographer.vkclient.presentation.comments.CommentsViewModel
import com.bibliographer.vkclient.presentation.main.MainViewModel
import com.bibliographer.vkclient.presentation.news.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    @Binds
    fun bindNewsFeedViewModel(viewModel: NewsFeedViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}