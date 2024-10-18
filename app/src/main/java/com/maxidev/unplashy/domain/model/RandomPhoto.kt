package com.maxidev.unplashy.domain.model

data class RandomPhoto(
    val id: String,
    val description: String,
    val slug: String,
    val width: Int,
    val height: Int,
    val altDescription: String,
    val fullImageUrl: String,
    val html: String,
    val download: String,
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