package com.maxidev.unplashy.di

import com.maxidev.unplashy.data.remote.apiservice.DetailService
import com.maxidev.unplashy.data.remote.apiservice.HomeService
import com.maxidev.unplashy.data.remote.apiservice.SearchService
import com.maxidev.unplashy.data.remote.apiservice.TopicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun providesHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Provides
    @Singleton
    fun providesRandomService(retrofit: Retrofit): DetailService =
        retrofit.create(DetailService::class.java)

    @Provides
    @Singleton
    fun providesSearchService(retrofit: Retrofit): SearchService =
        retrofit.create(SearchService::class.java)

    @Provides
    @Singleton
    fun providesTopicService(retrofit: Retrofit): TopicService =
        retrofit.create(TopicService::class.java)
}