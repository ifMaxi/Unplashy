package com.maxidev.unplashy.data.remote.apiservice

import com.maxidev.unplashy.data.remote.model.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

private const val SEARCH_PHOTOS = "search/photos"

interface SearchService {

    @GET(SEARCH_PHOTOS)
    suspend fun getSearchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchDto
}