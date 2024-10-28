package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Owners(
    val id: String? = "",
    val name: String? = ""
)