package com.maxidev.unplashy.data.remote.apiservice

import com.maxidev.unplashy.data.remote.model.PhotoIdDto
import retrofit2.http.GET
import retrofit2.http.Path

private const val PHOTO_ID = "photos/{id}"

interface DetailService {

    @GET(PHOTO_ID)
    suspend fun getPhotoId(
        @Path("id") id: String
    ): PhotoIdDto
}