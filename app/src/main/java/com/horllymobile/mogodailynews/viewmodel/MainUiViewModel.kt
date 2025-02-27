package com.horllymobile.mogodailynews.viewmodel

import androidx.lifecycle.ViewModel
import com.horllymobile.mogodailynews.data.MainUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainUiViewModel : ViewModel() {
    private val _mainUiState = MutableStateFlow(MainUiState())

    val mainUiState get() = _mainUiState.asStateFlow()

    fun updateUrl(url: String) {
        _mainUiState.update { state ->
            state.copy(
                newsUrl = url
            )
        }
    }
}