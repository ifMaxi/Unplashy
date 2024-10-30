package com.maxidev.unplashy.ui.topics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.maxidev.unplashy.R
import com.maxidev.unplashy.domain.model.Topic
import com.maxidev.unplashy.navigation.TopicScreen
import com.maxidev.unplashy.ui.components.MediumTopBarItem
import com.maxidev.unplashy.ui.theme.UnplashyTheme

fun NavGraphBuilder.topicScreen(navigateToTopicId: (String) -> Unit) {
    composable<TopicScreen> {
        val viewModel = hiltViewModel<TopicViewModel>()
        val state = viewModel.pagingTopic.collectAsLazyPagingItems()
        val lazyState = rememberLazyListState()

        TopicView(
            items = state,
            lazyState = lazyState,
            navigateToTopicId = navigateToTopicId
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopicView(
    modifier: Modifier = Modifier,
    items: LazyPagingItems<Topic>,
    lazyState: LazyListState,
    navigateToTopicId: (String) -> Unit
) {
    val pagingState = remember(items) { items }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopBarItem(
                title = R.string.topics,
                image = null,
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .statusBarsPadding(),
                state = lazyState,
                contentPadding = innerPadding,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(
                        count = pagingState.itemCount,
                        key = pagingState.itemKey { key -> key.id },
                        contentType = pagingState.itemContentType { contentType -> contentType.id }
                    ) { index ->
                        pagingState[index]?.let { item ->
                            TopicItem(
                                id = item.id,
                                title = item.title,
                                totalPhotos = item.totalPhotos,
                                coverPhoto = item.coverPhoto,
                                owner = item.owner,
                                navigateToTopicId = { navigateToTopicId(item.id) }
                            )
                        }
                    }
                }
            )
        }
    )
}

@Composable
private fun TopicItem(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    totalPhotos: Int,
    coverPhoto: String,
    owner: String,
    navigateToTopicId: (String) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(3))
            .clickable { navigateToTopicId(id) }
    ) {
        AsyncImage(
            model = coverPhoto,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
        )
        Text(
            modifier = Modifier
                .padding(10.dp),
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            style = TextStyle(
                shadow = Shadow(color = Color.Black, blurRadius = 8f)
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$totalPhotos photos",
                fontSize = 18.sp,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(color = Color.Black, blurRadius = 6f)
                )
            )
            Text(
                text = "Owner: $owner",
                fontSize = 18.sp,
                color = Color.White,
                style = TextStyle(
                    shadow = Shadow(color = Color.Black, blurRadius = 6f)
                )
            )
        }
    }
}

@Preview
@Composable
private fun TopicItemPreview() {
    UnplashyTheme {
        TopicItem(
            id = "interesset",
            title = "mollis",
            totalPhotos = 7396,
            coverPhoto = "mutat",
            owner = "eget",
            navigateToTopicId = {}
        )
    }
}