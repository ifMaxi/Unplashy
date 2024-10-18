package com.maxidev.unplashy.data.repository

import com.maxidev.unplashy.data.remote.apiservice.RandomService
import com.maxidev.unplashy.di.IoDispatcher
import com.maxidev.unplashy.domain.mapper.asDomain
import com.maxidev.unplashy.domain.model.RandomPhoto
import com.maxidev.unplashy.domain.repository.RandomPhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RandomPhotoRepositoryImpl @Inject constructor(
    private val apiService: RandomService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): RandomPhotoRepository {

    override suspend fun getRandomPhoto(): RandomPhoto =
        withContext(ioDispatcher) {
            apiService.getRandomPhoto().asDomain()
        }
}