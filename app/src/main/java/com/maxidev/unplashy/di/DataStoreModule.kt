package com.maxidev.unplashy.di

import android.content.Context
import com.maxidev.unplashy.data.preferences.SettingsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesSettingsDataStore(
        @ApplicationContext context: Context
    ) = SettingsDataStore(context)
}