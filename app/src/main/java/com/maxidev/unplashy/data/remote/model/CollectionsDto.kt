package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CollectionsDto(
    val id: String? = "",
    val title: String? = "",
    @SerialName("total_photos")
    val totalPhotos: Int? = 0,
    @SerialName("cover_photo")
    val coverPhoto: CoverPhoto? = CoverPhoto()
)