package com.maxidev.unplashy.domain.model

data class Topic(
    val id: String,
    val title: String,
    val totalPhotos: Int,
    val coverPhoto: String,
    val owner: String
)