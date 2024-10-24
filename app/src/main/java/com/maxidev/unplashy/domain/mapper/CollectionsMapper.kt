package com.maxidev.unplashy.domain.mapper

import com.maxidev.unplashy.data.remote.model.CollectionsDto
import com.maxidev.unplashy.domain.model.Collections

fun CollectionsDto.asDomain() =
    this.let { data ->
        Collections(
            id = data.id.orEmpty(),
            title = data.title.orEmpty(),
            totalPhotos = data.totalPhotos ?: 0,
            coverPhoto = data.coverPhoto?.urls?.regular.orEmpty()
        )
    }