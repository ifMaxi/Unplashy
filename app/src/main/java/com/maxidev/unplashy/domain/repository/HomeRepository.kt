package com.maxidev.unplashy.domain.repository

import androidx.paging.PagingData
import com.maxidev.unplashy.domain.model.Photos
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun fetchPhotos(): Flow<PagingData<Photos>>
}