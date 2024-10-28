package com.maxidev.unplashy.ui.topics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maxidev.unplashy.domain.model.Topic
import com.maxidev.unplashy.domain.repository.TopicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopicViewModel @Inject constructor(
    private val repository: TopicRepository
): ViewModel() {

    private val _pagingTopic = MutableStateFlow<PagingData<Topic>>(PagingData.empty())
    val pagingTopic = _pagingTopic

    init {
        topicData()
    }

    private fun topicData() =
        viewModelScope.launch {
            repository.fetchTopic()
                .cachedIn(viewModelScope)
                .collect { data -> _pagingTopic.value = data }
        }
}