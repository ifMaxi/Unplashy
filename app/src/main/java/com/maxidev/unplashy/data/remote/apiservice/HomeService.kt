package com.maxidev.unplashy.data.remote.apiservice

import com.maxidev.unplashy.data.remote.model.PhotosDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val PHOTOS = "photos"

// API endpoints for the home screen.
interface HomeService {

    @GET(PHOTOS)
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): List<PhotosDto>
}