package com.maxidev.unplashy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.maxidev.unplashy.ui.collections.collectionsScreen
import com.maxidev.unplashy.ui.details.detailScreen
import com.maxidev.unplashy.ui.home.homeView
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
            navigateToCollection = { navController.navigate(CollectionsScreen) },
            navigateToSearch = { navController.navigate(SearchScreen) },
            navigateToDetail = { id -> navController.navigate(DetailScreen(id = id)) }
        )
        searchPhotoScreen()
        collectionsScreen()
        detailScreen(
            navigateBack = { navController.popBackStack() },
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
    }
}

@Serializable data object HomePhotoScreen
@Serializable data object SearchScreen
@Serializable data object CollectionsScreen
@Serializable data class DetailScreen(val id: String)
@Serializable
data class ImageZoomScreen(
    val imageUrl: String,
    val width: Float,
    val height: Float
)