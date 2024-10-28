package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopicsDto(
    val id: String? = "",
    val title: String? = "",
    @SerialName("total_photos")
    val totalPhotos: Int? = 0,
    val owners: List<Owners?> = listOf(),
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto? = CoverPhoto()
)