package com.maxidev.unplashy.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.maxidev.unplashy.domain.model.Photos
import com.maxidev.unplashy.navigation.HomePhotoScreen
import com.maxidev.unplashy.ui.components.BottomBarItem
import com.maxidev.unplashy.ui.components.MediumTopBarItem
import com.maxidev.unplashy.ui.components.SmallFabItem
import com.maxidev.unplashy.ui.theme.UnplashyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun NavGraphBuilder.homeView(
    navigateToSearch: () -> Unit,
    navigateToTopic: () -> Unit,
    navigateToDetail: (String) -> Unit,
    navigateToSettings: () -> Unit
) {
    composable<HomePhotoScreen> {
        val viewModel = hiltViewModel<PhotosViewModel>()
        val photo = viewModel.pagingPhotos.collectAsLazyPagingItems()
        val lazyState = rememberLazyStaggeredGridState()

        PhotosContent(
            pagedContent = photo,
            lazyState = lazyState,
            navigateToSearch = navigateToSearch,
            navigateToDetail = navigateToDetail,
            navigateToTopic = navigateToTopic,
            navigateToSettings = navigateToSettings
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotosContent(
    modifier: Modifier = Modifier,
    pagedContent: LazyPagingItems<Photos>,
    lazyState: LazyStaggeredGridState,
    navigateToSearch: () -> Unit,
    navigateToTopic: () -> Unit,
    navigateToDetail: (String) -> Unit,
    navigateToSettings: () -> Unit
) {
    val context = LocalContext.current
    val photos = remember(pagedContent) { pagedContent }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var isRefreshing by remember { mutableStateOf(false) }
    val pullState = rememberPullToRefreshState()
    val scope = rememberCoroutineScope()
    val showBottomBar by remember {
        derivedStateOf { lazyState.firstVisibleItemIndex != 0 || lazyState.firstVisibleItemScrollOffset != 0 }
    }
    val showFabButton by remember { derivedStateOf { lazyState.firstVisibleItemIndex >= 10 } }
    val unsplashIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://unsplash.com"))
    val onRefresh: () -> Unit = {
        isRefreshing = true
        scope.launch {
            delay(2000)
            photos.refresh()
            isRefreshing = false
        }
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopBarItem(
                title = R.string.for_you,
                image = R.drawable.pylc5zrd_400x400,
                scrollBehavior = scrollBehavior,
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = showBottomBar,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
                content = {
                    BottomBarItem(
                        settingsIcon = Icons.Default.Settings,
                        topicsIcon = Icons.Default.Category,
                        searchIcon = Icons.Default.Search,
                        navigateToSearch = navigateToSearch,
                        fabIcon = Icons.Default.Add,
                        navigateToMainPage = { context.startActivity(unsplashIntent, null) },
                        navigateToTopics = navigateToTopic,
                        navigateToSettings = navigateToSettings
                    )
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = showFabButton,
                enter = fadeIn(),
                exit = fadeOut(),
                content = {
                    SmallFabItem(
                        smallFabIcon = Icons.Default.ArrowDropUp,
                        onClick = {
                            scope.launch { lazyState.animateScrollToItem(index = 0) }
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        // TODO: Fix pull to refresh.
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            state = pullState,
            indicator = {
                PullToRefreshDefaults.Indicator(
                    state = pullState,
                    isRefreshing = isRefreshing,
                    modifier = Modifier
                        .padding(top = 190.dp)
                        .align(Alignment.TopCenter)
                )
            },
            contentAlignment = Alignment.Center,
            content = {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier
                        .fillMaxSize(),
                    columns = StaggeredGridCells.Adaptive(250.dp),
                    state = lazyState,
                    contentPadding = innerPadding,
                    verticalItemSpacing = 10.dp,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        count = photos.itemCount,
                        key = photos.itemKey { key -> key.id },
                        contentType = photos.itemContentType { contentType -> contentType.id }
                    ) { item ->
                        photos[item]?.let { photos ->
                            PhotoItem(
                                id = photos.id,
                                imageRegular = photos.regular,
                                name = photos.name,
                                profileImageSmall = photos.profileImageLarge,
                                navigateToDetail = navigateToDetail
                            )
                        }
                    }
                }
            }
        )
    }
}

// Component for the normal grid.
@Composable
private fun PhotoItem(
    modifier: Modifier = Modifier,
    id: String,
    imageRegular: String,
    name: String,
    profileImageSmall: String,
    navigateToDetail: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.outline
                        ),
                        shape = CircleShape
                    )
                    .size(40.dp),
                model = profileImageSmall,
                contentDescription = name,
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
        AsyncImage(
            modifier = modifier
                .clip(RoundedCornerShape(4))
                .clickable { navigateToDetail(id) },
            model = imageRegular,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoItemPreview() {
    UnplashyTheme {
        PhotoItem(
            imageRegular = "image",
            name = "Brett Hart",
            profileImageSmall = "image",
            navigateToDetail = {},
            id = "id"
        )
    }
}