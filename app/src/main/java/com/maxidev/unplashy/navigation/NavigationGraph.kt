package com.maxidev.unplashy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maxidev.unplashy.ui.home.HomeView
import com.maxidev.unplashy.ui.random.RandomPhotoView
import kotlinx.serialization.Serializable

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        startDestination = HomePhotoScreen,
        navController = navController
    ) {
        composable<HomePhotoScreen> {
            HomeView(navController = navController)
        }
        composable<RandomPhotoScreen> {
            RandomPhotoView()
        }
    }
}

@Serializable data object HomePhotoScreen
@Serializable data object RandomPhotoScreen