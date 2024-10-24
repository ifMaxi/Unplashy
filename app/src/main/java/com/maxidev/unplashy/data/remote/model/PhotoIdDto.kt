package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PhotoIdDto(
    val id: String? = "",
    val description: String? = "",
    @SerialName("alt_description")
    val altDescription: String? = "",
    val slug: String? = "",
    @SerialName("created_at")
    val createdAt: String? = "",
    @SerialName("promoted_at")
    val promotedAt: String? = "",
    val color: String? = "",
    @SerialName("blur_hash")
    val blurHash: String? = "",
    val width: Int? = 0,
    val height: Int? = 0,
    val urls: Urls? = Urls(),
    val links: Links? = Links(),
    val user: User? = User(),
    val exif: Exif? = Exif(),
    val location: Location? = Location(),
    val tags: List<Tags>? = listOf()
)