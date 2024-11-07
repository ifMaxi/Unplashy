package com.maxidev.unplashy.ui.home

import androidx.paging.PagingData
import com.maxidev.unplashy.domain.model.Photos
import com.maxidev.unplashy.domain.repository.HomeRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @RelaxedMockK private lateinit var photosRepository: HomeRepository
    private lateinit var viewModel: PhotosViewModel

    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        viewModel = PhotosViewModel(photosRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @Test
    fun getPagingPhotosTest() = runBlocking {
        val expectedPhotos = listOf(
            Photos(
                id = "1",
                raw = "test",
                full = "test",
                regular ="test",
                small = "test",
                thumb = "test",
                download = "test",
                name = "test",
                profileImageSmall = "test",
                profileImageMedium = "test",
                profileImageLarge = "test"
            ),
            Photos(
                id = "2",
                raw = "test",
                full = "test",
                regular = "test",
                small = "test",
                thumb = "test",
                download = "test",
                name = "test",
                profileImageSmall = "test",
                profileImageMedium = "test",
                profileImageLarge = "test"
            )
        )

        coEvery { photosRepository.fetchPhotos() } returns flowOf(PagingData.from(expectedPhotos))

        val actualPhotos = viewModel.pagingPhotos.first()

        assertEquals(expectedPhotos, actualPhotos)

        coVerify(exactly = 1) { photosRepository.fetchPhotos() }
    }

    @Test
    fun isRefreshingTest() = runBlocking {
        val isRefreshing = viewModel.isRefreshing

        assertEquals(!isRefreshing, true)
    }
}