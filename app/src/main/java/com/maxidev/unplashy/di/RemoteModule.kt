package com.maxidev.unplashy.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.maxidev.unplashy.data.remote.ApiKeyInterceptor
import com.maxidev.unplashy.data.remote.CacheInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BASE_URL = "https://api.unsplash.com/"
private const val CONTENT_TYPE = "application/json"
private const val TIMEOUT = 15L

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providesRetrofit(
        @ApplicationContext context: Context
    ): Retrofit {

        val json = Json { ignoreUnknownKeys = true }
        val cache = Cache(
            directory = File(context.cacheDir, "cache"),
            maxSize = 50L * 1024L * 1024L
        )
        val okHttpClient = OkHttpClient.Builder()
            .cache(cache)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(ApiKeyInterceptor())
            .addNetworkInterceptor(CacheInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }
}