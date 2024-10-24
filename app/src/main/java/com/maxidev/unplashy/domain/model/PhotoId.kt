package com.maxidev.unplashy.domain.model

data class PhotoId(
    val id: String,
    val description: String,
    val altDescription: String,
    val createdAt: String,
    val promotedAt: String,
    val color: String,
    val blurHash: String,
    val slug: String,
    val width: Int,
    val height: Int,
    val fullUrl: String,
    val htmlUrl: String,
    val downloadUrl: String,
    val name: String,
    val profileImageUrl: String,
    val make: String,
    val model: String,
    val exposureTime: String,
    val aperture: String,
    val focalLength: String,
    val iso: Int,
    val city: String,
    val country: String,
    val type: String,
    val title: List<String>
)