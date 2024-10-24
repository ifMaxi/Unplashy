package com.maxidev.unplashy.di

import com.maxidev.unplashy.data.repository.CollectionsRepositoryImpl
import com.maxidev.unplashy.data.repository.DetailRepositoryImpl
import com.maxidev.unplashy.data.repository.HomeRepositoryImpl
import com.maxidev.unplashy.data.repository.SearchRepositoryImpl
import com.maxidev.unplashy.domain.repository.CollectionsRepository
import com.maxidev.unplashy.domain.repository.DetailRepository
import com.maxidev.unplashy.domain.repository.HomeRepository
import com.maxidev.unplashy.domain.repository.SearchRepository
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
    abstract fun bindRandomRepository(impl: DetailRepositoryImpl): DetailRepository

    @Binds
    abstract fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun bindCollectionRepository(impl: CollectionsRepositoryImpl): CollectionsRepository
}