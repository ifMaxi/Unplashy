package com.maxidev.unplashy.data.remote.apiservice

import com.maxidev.unplashy.data.remote.model.CollectionsDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val COLLECTIONS = "/collections"

interface CollectionsService {

    @GET(COLLECTIONS)
    suspend fun getCollections(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<CollectionsDto>
}