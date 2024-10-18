package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PhotosDto(
    val id: String? = "",
    val urls: Urls? = Urls(),
    val links: Links? = Links(),
    val user: User? = User()
)