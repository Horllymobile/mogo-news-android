package com.horllymobile.mogodailynews.network

import com.horllymobile.mogodailynews.services.NewsApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://192.168.0.126:3000/api/v1/"

val client = OkHttpClient()

val clientBuilder: OkHttpClient.Builder = client.newBuilder()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(clientBuilder.build())
    .build()


object ApiCalls {
    val newsFeeds : NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}