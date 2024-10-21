package com.maxidev.unplashy.ui.zoom

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import coil3.compose.AsyncImage
import com.maxidev.unplashy.navigation.ImageZoomScreen
import com.maxidev.unplashy.ui.theme.UnplashyTheme

fun NavGraphBuilder.imageZoomView(navigateBack: () -> Unit) {
    composable<ImageZoomScreen> { backStackEntry ->
        val args = backStackEntry.toRoute<ImageZoomScreen>()

        ImageZoomView(
            image = args.imageUrl,
            width = args.width,
            height = args.height,
            navigateBack = navigateBack
        )
    }
}

@Composable
fun ImageZoomView(
    modifier: Modifier = Modifier,
    image: String,
    width: Float,
    height: Float,
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .statusBarsPadding()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = navigateBack,
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .consumeWindowInsets(innerPadding)
                .background(color = if (isSystemInDarkTheme()) Color.Black else Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var scale by remember { mutableFloatStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }

            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(ratio = width / height ),
                content = {
                    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
                        scale = (scale * zoomChange).coerceAtLeast(1f)

                        val extraWidth = (scale - 1) * constraints.maxWidth
                        val extraHeight = (scale - 1) * constraints.maxHeight

                        val maxX = extraWidth / 2
                        val maxY = extraHeight / 2

                        offset = Offset(
                            x = (offset.x + scale * offsetChange.x).coerceIn(-maxX, maxX),
                            y = (offset.y + scale * offsetChange.y).coerceIn(-maxY, maxY)
                        )
                    }

                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                translationX = offset.x
                                translationY = offset.y
                            }
                            .transformable(state = state)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun ImageZoomViewPreview() {
    UnplashyTheme {
        ImageZoomView(
            image = "Image",
            width = 1f,
            height = 1f,
            navigateBack = {}
        )
    }
}