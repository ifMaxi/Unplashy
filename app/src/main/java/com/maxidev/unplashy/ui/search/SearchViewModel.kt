package com.maxidev.unplashy.ui.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.unplashy.domain.model.Search
import com.maxidev.unplashy.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
): ViewModel() {

    private val _searchPhotos = MutableStateFlow<PagingData<Search>>(PagingData.empty())
    val searchPhotos = _searchPhotos

    private val _query = mutableStateOf("")
    val query = _query

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun clearText() {
        _query.value = ""
    }

    fun searchData(query: String) =
        viewModelScope.launch {
            repository.fetchSearchedPhotos(query)
                .cachedIn(viewModelScope)
                .collect { searched -> _searchPhotos.value = searched }
        }
}