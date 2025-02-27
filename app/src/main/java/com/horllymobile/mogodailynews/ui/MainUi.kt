package com.horllymobile.mogodailynews.ui

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.horllymobile.mogodailynews.R
import com.horllymobile.mogodailynews.ui.screen.CategoryScreen
import com.horllymobile.mogodailynews.ui.screen.NewsScreen
import com.horllymobile.mogodailynews.ui.screen.ViewNews
import com.horllymobile.mogodailynews.ui.screen.navigation.MainNavigation
import com.horllymobile.mogodailynews.viewmodel.MainUiViewModel

sealed class NavRoute(val route: String) {
    data object Main : NavRoute("main")
    data object MainNav : NavRoute("main_nav")
    data object NewsFeed : NavRoute("news")
    data object ReadNews : NavRoute("read_news")
    data object Category : NavRoute("category")
}

@Composable
fun MainUi(
    modifier: Modifier = Modifier
) {
    val mainNavigation = rememberNavController()
    val navigationBar = rememberNavController()
    val mainUiViewModel: MainUiViewModel = viewModel()

    val mainUiState by mainUiViewModel.mainUiState.collectAsState()

    NavHost(
        navController = mainNavigation,
        startDestination = NavRoute.Main.route,
        modifier = modifier
    ) {
        navigation(startDestination = NavRoute.MainNav.route, route = NavRoute.Main.route) {
            composable(route = NavRoute.MainNav.route) {
                MainNavigation(
                    mainUiViewModel = mainUiViewModel,
                    navigationBar = navigationBar,
                    mainNavigation = mainNavigation
                )
            }
        }
        composable(route = NavRoute.ReadNews.route) {
            ViewNews(
                url = mainUiState.newsUrl,
                onBack = {
                    if (mainNavigation.currentBackStackEntry?.destination?.route != null) {
                        mainUiViewModel.updateUrl("")
                        mainNavigation.popBackStack()
                    }
                }
            )
        }
    }
}