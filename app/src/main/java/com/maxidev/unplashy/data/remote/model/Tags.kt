package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Tags(
    val type: String? = "",
    val title: String? = ""
)