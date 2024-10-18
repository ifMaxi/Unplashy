package com.maxidev.unplashy.di

import com.maxidev.unplashy.data.remote.apiservice.HomeService
import com.maxidev.unplashy.data.remote.apiservice.RandomService
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
    fun providesRandomService(retrofit: Retrofit): RandomService =
        retrofit.create(RandomService::class.java)
}