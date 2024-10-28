package com.maxidev.unplashy.domain.repository

import androidx.paging.PagingData
import com.maxidev.unplashy.domain.model.Topic
import com.maxidev.unplashy.domain.model.TopicId
import com.maxidev.unplashy.domain.model.TopicWithPhoto
import kotlinx.coroutines.flow.Flow

interface TopicRepository {

    fun fetchTopic(): Flow<PagingData<Topic>>

    suspend fun fetchTopicById(id: String): TopicId

    fun fetchTopicPhotos(id: String): Flow<PagingData<TopicWithPhoto>>
}