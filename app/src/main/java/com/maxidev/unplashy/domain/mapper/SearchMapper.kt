package com.maxidev.unplashy.domain.mapper

import com.maxidev.unplashy.data.remote.model.SearchDto
import com.maxidev.unplashy.domain.model.Search

fun SearchDto.asDomain() =
    this.results?.map { data ->
        Search(
            id = data.id.orEmpty(),
            regularImage = data.urls?.regular.orEmpty(),
        )
    }