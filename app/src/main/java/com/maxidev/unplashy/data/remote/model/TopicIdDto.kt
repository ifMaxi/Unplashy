package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class TopicIdDto(
    val id: String? = "",
    val title: String? = "",
    val description: String? = ""
)
