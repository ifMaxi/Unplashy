package com.maxidev.unplashy.domain.repository

import androidx.paging.PagingData
import com.maxidev.unplashy.domain.model.Collections
import kotlinx.coroutines.flow.Flow

interface CollectionsRepository {

    fun fetchCollections(): Flow<PagingData<Collections>>
}