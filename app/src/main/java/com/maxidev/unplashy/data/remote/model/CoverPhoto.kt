package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CoverPhoto(
    val urls: Urls? = Urls()
)