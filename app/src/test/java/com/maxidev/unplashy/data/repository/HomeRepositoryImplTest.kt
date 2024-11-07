package com.maxidev.unplashy.data.repository

import androidx.paging.PagingSource
import com.maxidev.unplashy.data.remote.apiservice.HomeService
import com.maxidev.unplashy.data.remote.model.Links
import com.maxidev.unplashy.data.remote.model.PhotosDto
import com.maxidev.unplashy.data.remote.model.Urls
import com.maxidev.unplashy.data.remote.model.User
import com.maxidev.unplashy.data.repository.paging.PhotosPagingSource
import com.maxidev.unplashy.domain.mapper.asDomain
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryImplTest {

    @RelaxedMockK private lateinit var api: HomeService
    private lateinit var pagingSource: PhotosPagingSource

    companion object {
        val response1 = listOf(
            PhotosDto(
                id = "1",
                urls = Urls(
                    raw = null, full = null, regular = null, small = null, thumb = null, smallS3 = null
                ),
                links = Links(html = null, download = null),
                user = User(name = null, profileImage = null),
            )
        )
        val response2 = listOf(
            PhotosDto(
                id = "1",
                urls = Urls(
                    raw = null, full = null, regular = null, small = null, thumb = null, smallS3 = null
                ),
                links = Links(html = null, download = null),
                user = User(name = null, profileImage = null),
            )
        )
    }

    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        pagingSource = PhotosPagingSource(api)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun testPagingSourceAppendSuccess() = runBlocking {

        coEvery { api.getPhotos(any(), any()) } returns response2

        val expectedResult = PagingSource.LoadResult.Page(
            data = response1.map { it.asDomain() },
            prevKey = null,
            nextKey = 2
        )

        assertEquals(
            expectedResult,
            pagingSource.load(
                PagingSource.LoadParams.Append(
                    key = 1,
                    loadSize = 1,
                    placeholdersEnabled = true
                )
            )
        )
    }

    @Test
    fun testPagingSourcePrependSuccess() = runBlocking {

        coEvery { api.getPhotos(any(), any()) } returns response1

        val expectedResult = PagingSource.LoadResult.Page(
            data = response2.map { it.asDomain() },
            prevKey = null,
            nextKey = 1
        )

        assertEquals(
            expectedResult,
            pagingSource.load(
                PagingSource.LoadParams.Prepend(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}