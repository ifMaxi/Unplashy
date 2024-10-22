package com.maxidev.unplashy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.maxidev.unplashy.ui.home.homeView
import com.maxidev.unplashy.ui.random.randomPhotoView
import com.maxidev.unplashy.ui.search.searchPhotoScreen
import com.maxidev.unplashy.ui.zoom.imageZoomView
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
        homeView(
            navigateToRandom = { navController.navigate(RandomPhotoScreen) },
            navigateToSearch = { navController.navigate(SearchScreen) }
        )
        randomPhotoView(
            navigateToImageZoom = { imageUrl, width, height ->
                navController.navigate(
                    ImageZoomScreen(
                        imageUrl = imageUrl,
                        width = width,
                        height = height
                    )
                )
            }
        )
        imageZoomView(navigateBack = { navController.popBackStack() })
        searchPhotoScreen()
    }
}

@Serializable data object HomePhotoScreen
@Serializable data object RandomPhotoScreen
@Serializable data class ImageZoomScreen(
    val imageUrl: String,
    val width: Float,
    val height: Float
)
@Serializable data object SearchScreen