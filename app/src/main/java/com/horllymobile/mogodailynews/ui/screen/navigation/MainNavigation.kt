package com.horllymobile.mogodailynews.ui.screen.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.horllymobile.mogodailynews.R
import com.horllymobile.mogodailynews.ui.NavRoute
import com.horllymobile.mogodailynews.ui.screen.CategoryScreen
import com.horllymobile.mogodailynews.ui.screen.NewsScreen
import com.horllymobile.mogodailynews.ui.screen.ViewNews
import com.horllymobile.mogodailynews.viewmodel.MainUiViewModel

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    mainUiViewModel: MainUiViewModel,
    navigationBar: NavHostController,
    mainNavigation: NavHostController
) {
    var selectedNav by rememberSaveable {
        mutableStateOf("")
    }
    val mainUiState by mainUiViewModel.mainUiState.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedNav == NavRoute.NewsFeed.route,
                    onClick = {
                        selectedNav = NavRoute.NewsFeed.route
                        navigationBar.navigate(NavRoute.NewsFeed.route)
                    },
                    label = {
                        Text(text = stringResource(R.string.news))
                    },
                    icon = {
                        Icon(Icons.Filled.Newspaper, contentDescription = stringResource(R.string.news))
                    }
                )

                NavigationBarItem(
                    selected = selectedNav == NavRoute.Category.route,
                    onClick = {
                        selectedNav = NavRoute.Category.route
                        navigationBar.navigate(NavRoute.Category.route)
                    },
                    label = {
                        Text(text = stringResource(R.string.category))
                    },
                    icon = {
                        Icon(Icons.Filled.Category, contentDescription = stringResource(R.string.category))
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navigationBar,
            startDestination = NavRoute.NewsFeed.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = NavRoute.NewsFeed.route) {
                NewsScreen(
                    mainUiViewModel = mainUiViewModel,
                    openNews = {
                        mainNavigation.navigate(NavRoute.ReadNews.route)
                    }
                )
            }
            composable(route = NavRoute.Category.route) {
                CategoryScreen()
            }
        }

    }
}