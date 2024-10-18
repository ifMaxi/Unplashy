package com.maxidev.unplashy.di

import com.maxidev.unplashy.data.repository.HomeRepositoryImpl
import com.maxidev.unplashy.data.repository.RandomPhotoRepositoryImpl
import com.maxidev.unplashy.domain.repository.HomeRepository
import com.maxidev.unplashy.domain.repository.RandomPhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindRandomRepository(impl: RandomPhotoRepositoryImpl): RandomPhotoRepository
}