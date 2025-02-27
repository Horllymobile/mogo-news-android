package com.horllymobile.mogodailynews.services

import com.horllymobile.mogodailynews.model.News
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApiService {
    @GET("news/get-news")
    suspend fun getNewsFeeds(): ApiResponse
}

data class ApiResponse(
    val data: List<NewsResponse>? = null,
    val message: String? = null
)

data class NewsResponse(
    val blog: String? = null,
    val news: List<News>? = null,
)