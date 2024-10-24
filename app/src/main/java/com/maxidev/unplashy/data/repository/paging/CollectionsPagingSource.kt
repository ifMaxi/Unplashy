package com.maxidev.unplashy.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.unplashy.data.remote.apiservice.CollectionsService
import com.maxidev.unplashy.domain.mapper.asDomain
import com.maxidev.unplashy.domain.model.Collections
import com.maxidev.unplashy.utils.Constants.PAGE_NUMBER
import retrofit2.HttpException
import java.io.IOException

class CollectionsPagingSource(
    private val apiService: CollectionsService
): PagingSource<Int, Collections>() {

    override fun getRefreshKey(state: PagingState<Int, Collections>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Collections> {

        return try {
            val nextPage = params.key ?: PAGE_NUMBER
            val response = apiService.getCollections(perPage = params.loadSize, page = nextPage)
            val prevKey = if (nextPage == 1) null else nextPage - 1
            val nextKey = if (response.isEmpty()) null else nextPage + 1

            LoadResult.Page(
                data = response.map { it.asDomain() },
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (ioException: IOException) {
            LoadResult.Error(ioException)
        } catch (httpException: HttpException) {
            LoadResult.Error(httpException)
        }
    }
}