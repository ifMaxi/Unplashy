package com.maxidev.unplashy.domain.repository

import com.maxidev.unplashy.domain.model.PhotoId

interface DetailRepository {

    suspend fun fetchPhotoId(id: String): PhotoId
}