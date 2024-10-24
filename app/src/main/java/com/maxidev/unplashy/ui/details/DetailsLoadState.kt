package com.maxidev.unplashy.ui.details

import com.maxidev.unplashy.domain.model.PhotoId

sealed interface DetailsLoadState {
    data class Success(val onSuccess: PhotoId? = null): DetailsLoadState
    data class Error(val onError: Exception): DetailsLoadState
}