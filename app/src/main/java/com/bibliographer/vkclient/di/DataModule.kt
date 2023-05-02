package com.bibliographer.vkclient.di

import android.content.Context
import com.bibliographer.vkclient.data.network.ApiFactory
import com.bibliographer.vkclient.data.network.ApiService
import com.bibliographer.vkclient.data.repository.NewsFeedRepositoryImpl
import com.bibliographer.vkclient.domain.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVKKeyStorage(context: Context): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}