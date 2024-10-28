package com.maxidev.unplashy.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maxidev.unplashy.data.remote.apiservice.TopicService
import com.maxidev.unplashy.domain.mapper.asDomain
import com.maxidev.unplashy.domain.model.TopicWithPhoto
import com.maxidev.unplashy.utils.Constants.PAGE_NUMBER
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TopicPhotosPagingSource @Inject constructor(
    private val apiService: TopicService,
    private val id: String
): PagingSource<Int, TopicWithPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, TopicWithPhoto>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopicWithPhoto> {

        return try {
            val page = params.key ?: PAGE_NUMBER
            val response = apiService.getTopicsWithPhotos(
                idOrSlug = id,
                page = page,
                perPage = params.loadSize
            )
            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (response.isEmpty()) null else page + 1

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