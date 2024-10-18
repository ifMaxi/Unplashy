package com.maxidev.unplashy.domain.mapper

import com.maxidev.unplashy.data.remote.model.RandomPhotoDto
import com.maxidev.unplashy.domain.model.RandomPhoto

fun RandomPhotoDto.asDomain() =
    this.let { data ->
        RandomPhoto(
            id = data.id.orEmpty(),
            description = data.description.orEmpty(),
            slug = data.slug.orEmpty(),
            width = data.width ?: 0,
            height = data.height ?: 0,
            altDescription = data.altDescription.orEmpty(),
            fullImageUrl = data.urls?.full.orEmpty(),
            html = data.links?.html.orEmpty(),
            download = data.links?.download.orEmpty(),
            name = data.user?.name.orEmpty(),
            profileImageUrl = data.user?.profileImage?.medium.orEmpty(),
            make = data.exif?.make.orEmpty(),
            model = data.exif?.model.orEmpty(),
            exposureTime = data.exif?.exposureTime.orEmpty(),
            aperture = data.exif?.aperture.orEmpty(),
            focalLength = data.exif?.focalLength.orEmpty(),
            iso = data.exif?.iso ?: 0,
            city = data.location?.city.orEmpty(),
            country = data.location?.country.orEmpty(),
            type = data.tags?.firstOrNull()?.type.orEmpty(),
            title = data.tags?.map { it.title.orEmpty() } ?: emptyList(),
        )
    }