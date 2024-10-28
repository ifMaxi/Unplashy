package com.maxidev.unplashy.ui.topics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.maxidev.unplashy.domain.model.TopicId
import com.maxidev.unplashy.domain.model.TopicWithPhoto
import com.maxidev.unplashy.navigation.TopicIdScreen
import com.maxidev.unplashy.ui.theme.UnplashyTheme

fun NavGraphBuilder.topicId(navigateToDetail: (String) -> Unit) {
    composable<TopicIdScreen> { backStackEntry ->
        val viewModel = hiltViewModel<TopicIdViewModel>()
        val topicId by viewModel.topicId.collectAsStateWithLifecycle()
        val topicPhotos = viewModel.topicPhotos.collectAsLazyPagingItems()
        val args = backStackEntry.toRoute<TopicIdScreen>()
        val lazyState = rememberLazyStaggeredGridState()

        LaunchedEffect(String) {
            viewModel.setTopicsPhotos(args.id)
        }

        TopicIdView(
            topicId = topicId ?: return@composable,
            topicPhotos = topicPhotos,
            lazyState = lazyState,
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
private fun TopicIdView(
    modifier: Modifier = Modifier,
    topicId: TopicId,
    topicPhotos: LazyPagingItems<TopicWithPhoto>,
    lazyState: LazyStaggeredGridState,
    navigateToDetail: (String) -> Unit
) {
    val topicState = remember(topicPhotos) { topicPhotos }

    LazyVerticalStaggeredGrid(
        modifier = modifier
            .fillMaxSize(),
        columns = StaggeredGridCells.Adaptive(150.dp),
        state = lazyState,
        contentPadding = PaddingValues(10.dp),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            TopicInformationItem(
                title = topicId.title,
                description = topicId.description
            )
        }
        items(
            count = topicState.itemCount,
            key = topicState.itemKey { key -> key.id },
            contentType = topicState.itemContentType { contentType -> contentType.id }
        ) { data ->
            topicState[data]?.let { photo ->
                PhotoItem(
                    imageUrl = photo.regularImage,
                    navigateToDetail = { navigateToDetail(photo.id) }
                )
            }
        }
    }
}

@Composable
private fun TopicInformationItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
        Text(
            text = description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PhotoItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    navigateToDetail: () -> Unit
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(3))
            .clickable { navigateToDetail() },
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}

@Preview
@Composable
private fun TopicInformationItemPreview() {
    UnplashyTheme {
        TopicInformationItem(
            title = "Sonet",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                    "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                    "voluptate velit esse cillum dolore eu fugiat nulla pariatur."
        )
    }
}