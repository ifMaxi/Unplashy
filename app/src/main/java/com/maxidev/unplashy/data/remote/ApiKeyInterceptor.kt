package com.maxidev.unplashy.data.remote

import com.maxidev.unplashy.BuildConfig
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

class ApiKeyInterceptor: Interceptor {

    private val apiKey = BuildConfig.clientId

    override fun intercept(chain: Interceptor.Chain): Response {

        var originalRequest = chain.request()
        val requestWithApiKey = originalRequest.newBuilder()
            .header("Authorization", "Client-ID $apiKey")
            .build()

        return chain.proceed(requestWithApiKey)
    }
}

class CacheInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(15, TimeUnit.MINUTES)
            .build()

        return response.newBuilder()
            .header("Cache-Control", cacheControl.toString())
            .build()
    }
}