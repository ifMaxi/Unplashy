package com.maxidev.unplashy.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.unplashy.domain.model.Collections
import com.maxidev.unplashy.domain.repository.CollectionsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(
    private val repository: CollectionsRepository
): ViewModel() {

    private val _pagingCollections = MutableStateFlow<PagingData<Collections>>(PagingData.empty())
    val pagingCollections = _pagingCollections

    init {
        collectionData()
    }

    private fun collectionData() =
        viewModelScope.launch {
            repository.fetchCollections()
                .cachedIn(viewModelScope)
                .collect { data -> _pagingCollections.value = data }
        }
}