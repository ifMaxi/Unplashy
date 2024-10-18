package com.maxidev.unplashy.ui.random

import com.maxidev.unplashy.domain.model.RandomPhoto

sealed interface RandomStatus {
    data class Success(val onSuccess: RandomPhoto? = null): RandomStatus
    data class Error(val onError: Exception): RandomStatus
}