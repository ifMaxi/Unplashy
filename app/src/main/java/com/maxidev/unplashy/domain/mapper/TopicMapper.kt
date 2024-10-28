package com.maxidev.unplashy.domain.mapper

import com.maxidev.unplashy.data.remote.model.TopicIdDto
import com.maxidev.unplashy.data.remote.model.TopicWithPhotoDto
import com.maxidev.unplashy.data.remote.model.TopicsDto
import com.maxidev.unplashy.domain.model.Topic
import com.maxidev.unplashy.domain.model.TopicId
import com.maxidev.unplashy.domain.model.TopicWithPhoto

fun TopicsDto.asDomain() =
    this.let { data ->
        Topic(
            id = data.id.orEmpty(),
            title = data.title.orEmpty(),
            totalPhotos = data.totalPhotos ?: 0,
            coverPhoto = data.coverPhoto?.urls?.regular.orEmpty(),
            owner = data.owners.firstOrNull()?.name.orEmpty()
        )
    }

fun TopicIdDto.asDomain() =
    this.let { data ->
        TopicId(
            id = data.id.orEmpty(),
            title = data.title.orEmpty(),
            description = data.description.orEmpty()
        )
    }

fun TopicWithPhotoDto.asDomain() =
    this.let { data ->
        TopicWithPhoto(
            id = data.id.orEmpty(),
            regularImage = data.urls?.regular.orEmpty()
        )
    }