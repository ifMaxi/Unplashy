package com.maxidev.unplashy.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxidev.unplashy.domain.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailRepository
): ViewModel() {

    private val _state: MutableStateFlow<DetailsLoadState> = MutableStateFlow(DetailsLoadState.Success(null))
    val state = _state.asStateFlow()

    fun loadDetails(id: String) =
        viewModelScope.launch {
            _state.value = try {
                DetailsLoadState.Success(onSuccess = repository.fetchPhotoId(id))
            } catch (e: IOException) {
                DetailsLoadState.Error(e)
            } catch (e: HttpException) {
                DetailsLoadState.Error(e)
            }
        }
}