package com.maxidev.unplashy.data.remote.apiservice

import com.maxidev.unplashy.data.remote.model.RandomPhotoDto
import retrofit2.http.GET

private const val RANDOM_PHOTOS = "photos/random"

interface RandomService {
    @GET(RANDOM_PHOTOS)
    suspend fun getRandomPhoto(): RandomPhotoDto
}