package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomPhotoDto(
    val id: String? = "",
    val description: String? = "",
    val slug: String? = "",
    val width: Int? = 0,
    val height: Int? = 0,
    @SerialName("alt_description")
    val altDescription: String? = "",
    val urls: Urls? = Urls(),
    val links: Links? = Links(),
    val user: User? = User(),
    val exif: Exif? = Exif(),
    val location: Location? = Location(),
    val tags: List<Tags>? = listOf()
)