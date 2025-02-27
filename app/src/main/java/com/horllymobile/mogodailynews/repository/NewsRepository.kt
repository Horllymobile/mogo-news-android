package com.horllymobile.mogodailynews.repository

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.horllymobile.mogodailynews.network.ApiCalls
import com.horllymobile.mogodailynews.viewmodel.NewsViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class NewsRepository : ViewModel() {

    fun getNewsFeed(newsViewModel: NewsViewModel) {
        newsViewModel.updateLoadingState(true)
        viewModelScope.launch {
            try {
                val result = ApiCalls.newsFeeds.getNewsFeeds()
                if (result.data != null) {
                    newsViewModel.updateNews(result.data)
                }
                newsViewModel.updateLoadingState(false)
                Log.d("Get News RESULT", "$result")
            } catch (e: HttpException) {
                newsViewModel.updateLoadingState(false)
                Log.d("Get News ERROR", e.message())
                e.message?.let {
                    newsViewModel.updateError(it)
                }
            } catch (e: IOException) {
                newsViewModel.updateLoadingState(false)
                Log.d("Get News ERROR", "${e.message}")
                e.message?.let {
                    newsViewModel.updateError(it)
                }
            }
            catch (e: Exception) {
                newsViewModel.updateLoadingState(false)
                e.message?.let {
                    newsViewModel.updateError(it)
                }
                Log.d("Get News ERROR", "${e.message}")
            }
        }
    }
}