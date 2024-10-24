package com.maxidev.unplashy.ui.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.maxidev.unplashy.navigation.DetailScreen
import com.maxidev.unplashy.ui.details.components.DetailContent

fun NavGraphBuilder.detailScreen(
    navigateBack: () -> Unit,
    navigateToImageZoom: (String, Float, Float) -> Unit
) {
    composable<DetailScreen> { backStackEntry ->
        val viewModel = hiltViewModel<DetailsViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val args = backStackEntry.toRoute<DetailScreen>()
        val scrollState = rememberScrollState()

        LaunchedEffect(String) {
            viewModel.loadDetails(args.id)
        }

        LoadStatus(
            load = state,
            scrollState = scrollState,
            navigateToImageZoom = navigateToImageZoom,
            navigateBack = navigateBack
        )
    }
}

@Composable
private fun LoadStatus(
    modifier: Modifier = Modifier,
    load: DetailsLoadState,
    scrollState: ScrollState,
    navigateToImageZoom: (String, Float, Float) -> Unit,
    navigateBack: () -> Unit
) {

    when (load) {
        is DetailsLoadState.Error -> Text("Error")
        is DetailsLoadState.Success -> {
            DetailContent(
                modifier = modifier,
                detail = load.onSuccess ?: return,
                scrollState = scrollState,
                navigateToImageZoom = navigateToImageZoom,
                navigateBack = navigateBack
            )
        }
    }
}