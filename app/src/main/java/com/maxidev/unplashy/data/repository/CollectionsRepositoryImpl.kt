package com.maxidev.unplashy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxidev.unplashy.data.remote.apiservice.CollectionsService
import com.maxidev.unplashy.data.repository.paging.CollectionsPagingSource
import com.maxidev.unplashy.domain.model.Collections
import com.maxidev.unplashy.domain.repository.CollectionsRepository
import com.maxidev.unplashy.utils.Constants.INITIAL_LOAD_SIZE
import com.maxidev.unplashy.utils.Constants.PAGE_SIZE
import com.maxidev.unplashy.utils.Constants.PREFETCH_DISTANCE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CollectionsRepositoryImpl @Inject constructor(
    private val apiService: CollectionsService
): CollectionsRepository {

    override fun fetchCollections(): Flow<PagingData<Collections>> {

        val sourceFactory = { CollectionsPagingSource(apiService) }

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