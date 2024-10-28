package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class TopicWithPhotoDto(
    val id: String? = "",
    val urls: Urls? = Urls()
)