package com.maxidev.unplashy.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.unplashy.data.remote.apiservice.SearchService
import com.maxidev.unplashy.domain.mapper.asDomain
import com.maxidev.unplashy.domain.model.Search
import com.maxidev.unplashy.utils.Constants.PAGE_NUMBER
import retrofit2.HttpException
import java.io.IOException

class SearchPagingSource(
    private val query: String,
    private val apiService: SearchService
): PagingSource<Int, Search>() {

    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {

        return try {
            val nextPage = params.key ?: PAGE_NUMBER
            val response = apiService.getSearchPhotos(
                query = query,
                page = nextPage,
                perPage = params.loadSize
            )
            val responseDomain = response.asDomain()
            val prevKey = if (nextPage == 1) null else nextPage - 1
            val nextKey = if (responseDomain.isNullOrEmpty()) null else nextPage + 1

            LoadResult.Page(
                data = responseDomain.orEmpty(),
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