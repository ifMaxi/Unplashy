package com.maxidev.unplashy.ui.random

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.unplashy.domain.repository.RandomPhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val repository: RandomPhotoRepository
): ViewModel() {

    private val _loadState: MutableStateFlow<RandomStatus> = MutableStateFlow(RandomStatus.Success())
    val loadState = _loadState.asStateFlow()

    init {
        Log.d("RandomViewModel", "init")
        randomPhoto()
    }

    private fun randomPhoto() {
        viewModelScope.launch {
            _loadState.value = try {
                RandomStatus.Success(onSuccess = repository.getRandomPhoto())
            } catch (e: IOException) {
                RandomStatus.Error(onError = e)
            } catch (e: HttpException) {
                RandomStatus.Error(onError = e)
            }
        }
    }

    override fun onCleared() {
        Log.d("RandomViewModel", "onCleared")
        super.onCleared()
    }
}