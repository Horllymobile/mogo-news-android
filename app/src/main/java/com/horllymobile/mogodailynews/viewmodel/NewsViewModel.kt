package com.horllymobile.mogodailynews.viewmodel

import androidx.lifecycle.ViewModel
import com.horllymobile.mogodailynews.data.ApiError
import com.horllymobile.mogodailynews.data.NewsUiScreenState
import com.horllymobile.mogodailynews.services.NewsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsViewModel : ViewModel() {
    private val _newsUiScreenState = MutableStateFlow(NewsUiScreenState())


    val newsUiScreenState get() = _newsUiScreenState.asStateFlow()

    fun updateNews(data: List<NewsResponse>) {
        _newsUiScreenState.update { state ->
            state.copy(
                news = data
            )
        }
    }

    fun updateLoadingState(status: Boolean) {
        _newsUiScreenState.update { state ->
            state.copy(
                isLoading = status
            )
        }
    }

    fun updateError(message: String) {
        _newsUiScreenState.update { state ->
            state.copy(
                error = ApiError(status = true, message = message)
            )
        }
    }
}