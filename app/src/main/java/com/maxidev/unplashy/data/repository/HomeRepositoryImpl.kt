package com.maxidev.unplashy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxidev.unplashy.data.remote.apiservice.HomeService
import com.maxidev.unplashy.data.repository.paging.PhotosPagingSource
import com.maxidev.unplashy.domain.model.Photos
import com.maxidev.unplashy.domain.repository.HomeRepository
import com.maxidev.unplashy.utils.Constants.INITIAL_LOAD_SIZE
import com.maxidev.unplashy.utils.Constants.PAGE_SIZE
import com.maxidev.unplashy.utils.Constants.PREFETCH_DISTANCE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: HomeService
): HomeRepository {

    override fun fetchPhotos(): Flow<PagingData<Photos>> {

        val sourceFactory = { PhotosPagingSource(apiService) }

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