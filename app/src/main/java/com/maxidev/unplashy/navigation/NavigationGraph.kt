package com.maxidev.unplashy.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.maxidev.unplashy.ui.details.detailScreen
import com.maxidev.unplashy.ui.home.homeView
import com.maxidev.unplashy.ui.search.searchPhotoScreen
import com.maxidev.unplashy.ui.topics.topicId
import com.maxidev.unplashy.ui.topics.topicScreen
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
            navigateToTopic = { navController.navigate(TopicScreen) },
            navigateToSearch = { navController.navigate(SearchScreen) },
            navigateToDetail = { id ->
                navController.navigate(DetailScreen(id = id))
            }
        )
        searchPhotoScreen(
            navigateToDetail = { id ->
                navController.navigate(DetailScreen(id = id))
            }
        )
        topicScreen(
            navigateToTopicId = { id ->
                navController.navigate(TopicIdScreen(id = id))
            }
        )
        topicId(
            navigateToDetail = { id ->
                navController.navigate(DetailScreen(id = id))
            }
        )
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
        imageZoomView(
            navigateBack = { navController.popBackStack() }
        )
    }
}

@Serializable data object HomePhotoScreen
@Serializable data object SearchScreen
@Serializable data object TopicScreen
@Serializable data class TopicIdScreen(val id: String)
@Serializable data class DetailScreen(val id: String)
@Serializable
data class ImageZoomScreen(
    val imageUrl: String,
    val width: Float,
    val height: Float
)