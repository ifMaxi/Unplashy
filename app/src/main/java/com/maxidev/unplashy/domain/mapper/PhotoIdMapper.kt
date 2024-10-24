package com.maxidev.unplashy.domain.mapper

import com.maxidev.unplashy.data.remote.model.PhotoIdDto
import com.maxidev.unplashy.domain.model.PhotoId

fun PhotoIdDto.asDomain() =
    this.let { data ->
        PhotoId(
            id = data.id.orEmpty(),
            description = data.description.orEmpty(),
            altDescription = data.altDescription.orEmpty(),
            createdAt = data.createdAt.orEmpty(),
            promotedAt = data.promotedAt.orEmpty(),
            color = data.color.orEmpty(),
            blurHash = data.blurHash.orEmpty(),
            slug = data.slug.orEmpty(),
            width = data.width ?: 0,
            height = data.height ?: 0,
            fullUrl = data.urls?.full.orEmpty(),
            htmlUrl = data.links?.html.orEmpty(),
            downloadUrl = data.links?.download.orEmpty(),
            name = data.user?.name.orEmpty(),
            profileImageUrl = data.user?.profileImage?.large.orEmpty(),
            make = data.exif?.make.orEmpty(),
            model = data.exif?.model.orEmpty(),
            exposureTime = data.exif?.exposureTime.orEmpty(),
            aperture = data.exif?.aperture.orEmpty(),
            focalLength = data.exif?.focalLength.orEmpty(),
            iso = data.exif?.iso ?: 0,
            city = data.location?.city.orEmpty(),
            country = data.location?.country.orEmpty(),
            type = data.tags?.firstOrNull()?.type.orEmpty(),
            title = data.tags?.map { it.title.orEmpty() } ?: emptyList()
        )
    }