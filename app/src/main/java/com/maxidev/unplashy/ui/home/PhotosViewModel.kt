package com.maxidev.unplashy.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.unplashy.domain.model.Photos
import com.maxidev.unplashy.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repository: HomeRepository
): ViewModel() {

    private val _pagingPhotos = MutableStateFlow<PagingData<Photos>>(PagingData.empty())
    val pagingPhotos = _pagingPhotos

    private val refreshingRequest = Channel<Unit> { _pagingPhotos.value }

    var isRefreshing by mutableStateOf(false)
        private set

    init {
        fetchPhotos()

        viewModelScope.launch {
            for (request in refreshingRequest) {
                isRefreshing = true
                try {
                    _pagingPhotos.value
                    delay(2000)
                } finally {
                    isRefreshing = false
                }
            }
        }
    }

    fun refresh() = refreshingRequest.trySend(Unit)

    private fun fetchPhotos() = viewModelScope.launch {
        repository.fetchPhotos()
            .cachedIn(viewModelScope)
            .distinctUntilChanged()
            .collect { data -> _pagingPhotos.value = data }
    }
}