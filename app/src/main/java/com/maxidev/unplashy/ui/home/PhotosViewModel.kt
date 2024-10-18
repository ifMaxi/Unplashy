package com.maxidev.unplashy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.unplashy.domain.model.Photos
import com.maxidev.unplashy.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    init {
        fetchPhotos()
    }

    private fun fetchPhotos() = viewModelScope.launch {
        repository.fetchPhotos()
            .cachedIn(viewModelScope)
            .distinctUntilChanged()
            .collect { data -> _pagingPhotos.value = data }
    }
}