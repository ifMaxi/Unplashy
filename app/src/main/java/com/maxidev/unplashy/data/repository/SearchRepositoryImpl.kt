package com.maxidev.unplashy.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maxidev.unplashy.data.remote.apiservice.SearchService
import com.maxidev.unplashy.data.repository.paging.SearchPagingSource
import com.maxidev.unplashy.domain.model.Search
import com.maxidev.unplashy.domain.repository.SearchRepository
import com.maxidev.unplashy.utils.Constants.INITIAL_LOAD_SIZE
import com.maxidev.unplashy.utils.Constants.MAX_SIZE
import com.maxidev.unplashy.utils.Constants.PAGE_SIZE
import com.maxidev.unplashy.utils.Constants.PREFETCH_DISTANCE
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@OptIn(FlowPreview::class)
class SearchRepositoryImpl @Inject constructor(
    private val apiService: SearchService
): SearchRepository {

    override fun fetchSearchedPhotos(query: String): Flow<PagingData<Search>> {

        val sourceFactory = { SearchPagingSource(apiService = apiService, query = query) }

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE,
                enablePlaceholders = true,
                initialLoadSize = INITIAL_LOAD_SIZE,
                maxSize = MAX_SIZE
            ),
            pagingSourceFactory = sourceFactory
        ).flow
            .distinctUntilChanged()
            .debounce(400L)
    }
}