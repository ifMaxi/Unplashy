package com.maxidev.unplashy.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    val results: List<Result>? = listOf()
)

@Serializable
data class Result(
    val id: String? = "",
    val urls: Urls? = Urls()
)