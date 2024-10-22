package com.maxidev.unplashy.domain.repository

import androidx.paging.PagingData
import com.maxidev.unplashy.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun fetchSearchedPhotos(query: String): Flow<PagingData<Search>>
}