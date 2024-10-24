package com.maxidev.unplashy.data.repository

import com.maxidev.unplashy.data.remote.apiservice.DetailService
import com.maxidev.unplashy.di.IoDispatcher
import com.maxidev.unplashy.domain.mapper.asDomain
import com.maxidev.unplashy.domain.model.PhotoId
import com.maxidev.unplashy.domain.repository.DetailRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val apiService: DetailService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): DetailRepository {

    override suspend fun fetchPhotoId(id: String): PhotoId =
        withContext(ioDispatcher) {
            apiService.getPhotoId(id).asDomain()
        }
}