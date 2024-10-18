package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val name: String? = "",
    val city: String? = "",
    val country: String? = ""
)