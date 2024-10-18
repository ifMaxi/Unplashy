package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val html: String? = "",
    val download: String? = ""
)