package com.horllymobile.mogodailynews.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.horllymobile.mogodailynews.R
import com.horllymobile.mogodailynews.model.News
import com.horllymobile.mogodailynews.repository.NewsRepository
import com.horllymobile.mogodailynews.services.NewsResponse
import com.horllymobile.mogodailynews.viewmodel.MainUiViewModel
import com.horllymobile.mogodailynews.viewmodel.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    mainUiViewModel: MainUiViewModel,
    openNews: () -> Unit
) {
    val newsViewModel: NewsViewModel = viewModel()
    val newsRepository: NewsRepository = viewModel()

    val newsUiScreenState by newsViewModel.newsUiScreenState.collectAsState()

    val scope = rememberCoroutineScope()

    LaunchedEffect(newsUiScreenState.news == null) {
        scope.launch {
            newsRepository.getNewsFeed(newsViewModel)
        }
    }



    if (newsUiScreenState.isLoading) {
        LoadingScreen()
    } else {
        Scaffold(
            modifier = modifier.fillMaxSize(),

            topBar = {
                CenterAlignedTopAppBar(
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    newsRepository.getNewsFeed(newsViewModel)
                                }
                            }
                        ) {
                            Icon(Icons.Filled.Replay, contentDescription = stringResource(R.string.refresh))
                        }
                    },
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
//                            Image(
//                                painter = painterResource(R.drawable.mogo_logo),
//                                contentDescription = stringResource(R.string.top_news),
//                                modifier = Modifier.size(34.dp)
//                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = stringResource(R.string.top_news))
                        }
                    }
                )
            }
        ) { innerPadding ->
            val newsItems = newsUiScreenState.news
            if (newsItems != null) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(innerPadding),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(newsItems) { news ->
                        NewsRow(
                            data = news,
                            mainUiViewModel = mainUiViewModel,
                            openNews = openNews,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.mogo_logo),
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier.size(120.dp)
            )
            Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(20.dp))
            LinearProgressIndicator()
        }
    }
}

@Composable
fun NewsRow(
    modifier: Modifier = Modifier,
    data: NewsResponse,
    mainUiViewModel: MainUiViewModel,
    openNews: () -> Unit
) {
    val news = data.news?.sortedBy {
        it.time.parseTimeToMinutes()
    }
    Column(
        modifier = modifier.fillMaxWidth().padding(horizontal = 5.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            data.blog?.let { Text(
                text = it.titleCase(),
                style = MaterialTheme.typography.headlineSmall
            ) }

        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
//            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (news != null) {
                items(data.news) { news ->
                    NewsCard(
                        news = news,
                        openNews = {
                            mainUiViewModel.updateUrl(news.link)
                            openNews()
                        }
                    )
                }
            }
        }
    }
}

fun String.parseTimeToMinutes(): Int {
    val parts = this.split(" ")
    if (parts.size != 2) return 0  // Fallback in case of unexpected format

    val value = parts[0].toIntOrNull() ?: return 0
    return when (parts[1]) {
        "min", "mins" -> value
        "hour", "hours" -> value * 60
        else -> 0
    }
}

@Composable
fun NewsCard(
    modifier: Modifier = Modifier,
    openNews: () -> Unit,
    news: News
) {
    Card(
        modifier = modifier.width(200.dp).height(150.dp).padding(horizontal = 4.dp).clickable { openNews() }
    ) {
        Column(
            modifier = Modifier.padding(8.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = news.title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                softWrap = true,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = news.time,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


fun String.titleCase(): String {
    return "${this[0].uppercase()}${this.substring(1)}"
}