package com.maxidev.unplashy.domain.repository

import com.maxidev.unplashy.domain.model.RandomPhoto

interface RandomPhotoRepository {

    suspend fun getRandomPhoto(): RandomPhoto
}