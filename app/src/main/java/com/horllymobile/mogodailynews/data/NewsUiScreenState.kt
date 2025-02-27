package com.horllymobile.mogodailynews.data

import com.horllymobile.mogodailynews.services.NewsResponse

data class NewsUiScreenState(
    val isLoading: Boolean = false,
    val news: List<NewsResponse>? = null,
    val error: ApiError = ApiError(
        status = false,
        message = ""
    )
)

data class ApiError(
    val status: Boolean = false,
    val message: String = "",
)