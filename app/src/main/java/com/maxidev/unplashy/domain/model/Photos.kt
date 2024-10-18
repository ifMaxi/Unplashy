package com.maxidev.unplashy.domain.model

data class Photos(
    val id: String,
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    val download: String,
    val name: String,
    val profileImageSmall: String,
    val profileImageMedium: String,
    val profileImageLarge: String
)