package com.maxidev.unplashy.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.unplashy.data.remote.apiservice.HomeService
import com.maxidev.unplashy.domain.mapper.asDomain
import com.maxidev.unplashy.domain.model.Photos
import com.maxidev.unplashy.utils.Constants.PAGE_NUMBER
import retrofit2.HttpException
import java.io.IOException

class PhotosPagingSource(
    private val apiService: HomeService
): PagingSource<Int, Photos>() {

    override fun getRefreshKey(state: PagingState<Int, Photos>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photos> {

        return try {
            val nextPage = params.key ?: PAGE_NUMBER
            val response = apiService.getPhotos(perPage = params.loadSize, page = nextPage)
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