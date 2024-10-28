package com.maxidev.unplashy.data.remote.apiservice

import com.maxidev.unplashy.data.remote.model.TopicIdDto
import com.maxidev.unplashy.data.remote.model.TopicWithPhotoDto
import com.maxidev.unplashy.data.remote.model.TopicsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val TOPICS = "topics"
private const val TOPIC_ID = "topics/{id_or_slug}"
private const val TOPIC_ID_PHOTOS = "topics/{id_or_slug}/photos"

interface TopicService {

    @GET(TOPICS)
    suspend fun getTopics(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<TopicsDto>

    @GET(TOPIC_ID)
    suspend fun getTopicById(
        @Path("id_or_slug") idOrSlug: String
    ): TopicIdDto

    @GET(TOPIC_ID_PHOTOS)
    suspend fun getTopicsWithPhotos(
        @Path("id_or_slug") idOrSlug: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<TopicWithPhotoDto>
}