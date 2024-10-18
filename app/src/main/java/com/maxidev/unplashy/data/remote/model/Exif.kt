package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Exif(
    val make: String? = "",
    val model: String? = "",
    @SerialName("exposure_time")
    val exposureTime: String? = "",
    val aperture: String? = "",
    @SerialName("focal_length")
    val focalLength: String? = "",
    val iso: Int? = 0
)