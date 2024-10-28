package com.maxidev.unplashy.ui.topics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.unplashy.domain.model.TopicId
import com.maxidev.unplashy.domain.model.TopicWithPhoto
import com.maxidev.unplashy.domain.repository.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicIdViewModel @Inject constructor(
    private val repository: TopicRepository
): ViewModel() {

    private val _topicPhotos = MutableStateFlow<PagingData<TopicWithPhoto>>(PagingData.empty())
    val topicPhotos = _topicPhotos

    private val _topicId = MutableStateFlow<TopicId?>(null)
    val topicId = _topicId.asStateFlow()

    fun setTopicsPhotos(id: String) =
        viewModelScope.launch {
            Log.d("TopicIdViewModel", "Function called")
            val asyncTopicId = async { _topicId.value = repository.fetchTopicById(id) }
            val asyncTopicPhotos = async {
                repository.fetchTopicPhotos(id = id)
                    .cachedIn(viewModelScope)
                    .collect { photo -> _topicPhotos.value = photo }
            }

            asyncTopicId.await()
            asyncTopicPhotos.await()
        }
}