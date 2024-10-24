package com.maxidev.unplashy.ui.collections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.maxidev.unplashy.domain.model.Collections
import com.maxidev.unplashy.navigation.CollectionsScreen
import com.maxidev.unplashy.ui.theme.UnplashyTheme

fun NavGraphBuilder.collectionsScreen() {
    composable<CollectionsScreen> {
        val viewModel = hiltViewModel<CollectionsViewModel>()
        val state = viewModel.pagingCollections.collectAsLazyPagingItems()
        val lazyState = rememberLazyStaggeredGridState()

        CollectionsView(
            items = state,
            lazyState = lazyState
        )
    }
}

@Composable
private fun CollectionsView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Collections>,
    lazyState: LazyStaggeredGridState
) {
    val pagingState = remember(items) { items }

    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = StaggeredGridCells.Adaptive(150.dp),
        state = lazyState,
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalItemSpacing = 10.dp
    ) {
        items(
            count = pagingState.itemCount,
            key = pagingState.itemKey { key -> key.id },
            contentType = pagingState.itemContentType { contentType -> contentType.id }
        ) { index ->
            pagingState[index]?.let { item ->
                CollectionItem(
                    id = item.id,
                    title = item.title,
                    totalPhotos = item.totalPhotos,
                    coverPhoto = item.coverPhoto
                )
            }
        }
    }
}

@Composable
private fun CollectionItem(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    totalPhotos: Int,
    coverPhoto: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(3))
    ) {
        AsyncImage(
            model = coverPhoto,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(color = Color.Black, blurRadius = 4f)
                )
            )
            Text(
                text = "$totalPhotos photos",
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(color = Color.Black, blurRadius = 4f)
                )
            )
        }
    }
}

@Preview
@Composable
private fun CollectionItemPreview() {
    UnplashyTheme {
        CollectionItem(
            id = "interesset", title = "mollis", totalPhotos = 7396, coverPhoto = "mutat"
        )
    }
}