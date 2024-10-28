package com.maxidev.unplashy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxidev.unplashy.data.remote.apiservice.TopicService
import com.maxidev.unplashy.data.repository.paging.TopicPhotosPagingSource
import com.maxidev.unplashy.data.repository.paging.TopicsPagingSource
import com.maxidev.unplashy.di.IoDispatcher
import com.maxidev.unplashy.domain.mapper.asDomain
import com.maxidev.unplashy.domain.model.Topic
import com.maxidev.unplashy.domain.model.TopicId
import com.maxidev.unplashy.domain.model.TopicWithPhoto
import com.maxidev.unplashy.domain.repository.TopicRepository
import com.maxidev.unplashy.utils.Constants.INITIAL_LOAD_SIZE
import com.maxidev.unplashy.utils.Constants.PAGE_SIZE
import com.maxidev.unplashy.utils.Constants.PREFETCH_DISTANCE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val apiService: TopicService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): TopicRepository {

    override fun fetchTopic(): Flow<PagingData<Topic>> {

        val sourceFactory = { TopicsPagingSource(apiService) }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = true
            ),
            pagingSourceFactory = sourceFactory,
        ).flow
    }

    override suspend fun fetchTopicById(id: String): TopicId =
        withContext(ioDispatcher) {
            apiService.getTopicById(idOrSlug = id).asDomain()
        }

    override fun fetchTopicPhotos(id: String): Flow<PagingData<TopicWithPhoto>> {

        val sourceFactory = { TopicPhotosPagingSource(apiService, id) }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = true,
                initialLoadSize = INITIAL_LOAD_SIZE
            ),
            pagingSourceFactory = sourceFactory,
        ).flow
    }
}