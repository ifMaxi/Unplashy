package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String? = "",
    @SerialName("profile_image")
    val profileImage: ProfileImage? = ProfileImage()
)