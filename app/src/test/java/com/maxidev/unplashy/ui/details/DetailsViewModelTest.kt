package com.maxidev.unplashy.ui.details

import com.maxidev.unplashy.domain.model.PhotoId
import com.maxidev.unplashy.domain.repository.DetailRepository
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
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
class DetailsViewModelTest {

    @RelaxedMockK private lateinit var mockDetailsRepository: DetailRepository
    private lateinit var viewModel: DetailsViewModel

    companion object {
        val photoDetail = PhotoId(
            id = "1",
            description = "",
            altDescription = "",
            createdAt = "",
            promotedAt = "",
            color = "",
            blurHash = "",
            slug = "",
            width = 0,
            height = 0,
            fullUrl = "",
            htmlUrl = "",
            downloadUrl = "",
            name = "",
            profileImageUrl = "",
            make = "",
            model = "",
            exposureTime = "",
            aperture = "",
            focalLength = "",
            iso = 0,
            city = "",
            country = "",
            type = "",
            title = listOf()
        )
    }

    @Before
    fun setUp() {
        val dispatcher = UnconfinedTestDispatcher()
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
        viewModel = DetailsViewModel(mockDetailsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }


    @Test
    fun getSuccessStateTest() = runBlocking {

        val id = photoDetail.id
        val expectedState = DetailsLoadState.Success(photoDetail)

        // Prepare
        coEvery { mockDetailsRepository.fetchPhotoId(id) } returns photoDetail

        // Execute
        viewModel.loadDetails(id)

        // Verify
        assertEquals(expectedState, viewModel.state.value)
        coVerify(exactly = 1) { mockDetailsRepository.fetchPhotoId(id) }
    }
}