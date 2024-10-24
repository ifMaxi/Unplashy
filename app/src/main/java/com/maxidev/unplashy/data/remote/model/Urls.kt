package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Urls(
    val raw: String? = "",
    val full: String? = "",
    val regular: String? = "",
    val small: String? = "",
    val thumb: String? = "",
    val smallS3: String? = ""
)