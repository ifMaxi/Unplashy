package com.maxidev.unplashy.domain.mapper

import com.maxidev.unplashy.data.remote.model.PhotosDto
import com.maxidev.unplashy.domain.model.Photos

fun PhotosDto.asDomain() =
    this.let { data ->
        Photos(
            id = data.id.orEmpty(),
            raw = data.urls?.raw.orEmpty(),
            full = data.urls?.full.orEmpty(),
            regular = data.urls?.regular.orEmpty(),
            small = data.urls?.small.orEmpty(),
            thumb = data.urls?.thumb.orEmpty(),
            download = data.links?.download.orEmpty(),
            name = data.user?.name.orEmpty(),
            profileImageSmall = data.user?.profileImage?.small.orEmpty(),
            profileImageMedium = data.user?.profileImage?.medium.orEmpty(),
            profileImageLarge = data.user?.profileImage?.large.orEmpty()
        )
    }