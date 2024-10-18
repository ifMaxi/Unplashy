package com.maxidev.unplashy.ui.home

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
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.maxidev.unplashy.domain.model.Photos
import com.maxidev.unplashy.navigation.RandomPhotoScreen
import com.maxidev.unplashy.ui.components.BottomBarItem
import com.maxidev.unplashy.ui.theme.UnplashyTheme

@Composable
fun HomeView(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PhotosViewModel = hiltViewModel()
) {
    val photo = viewModel.pagingPhotos.collectAsLazyPagingItems()
    val lazyState = rememberLazyStaggeredGridState()

    PhotosContent(
        modifier = modifier,
        pagedContent = photo,
        lazyState = lazyState,
        navigateToRandom = { navController.navigate(RandomPhotoScreen) },
        navigateToSearch = { /* Navigate to search screen */ }
    )
}

@Composable
private fun PhotosContent(
    modifier: Modifier = Modifier,
    pagedContent: LazyPagingItems<Photos>,
    lazyState: LazyStaggeredGridState,
    navigateToRandom: () -> Unit,
    navigateToSearch: () -> Unit
) {
    val photos = remember(pagedContent) { pagedContent }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomBarItem(
                fabText = "Random",
                searchIcon = Icons.Default.Search,
                fabIcon = Icons.Default.Photo,
                onFabClick = navigateToRandom,
                onSearchClick = navigateToSearch
            )
        }
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Adaptive(250.dp),
            state = lazyState,
            contentPadding = innerPadding,
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item(span = StaggeredGridItemSpan.SingleLane) {
                TopTextItem(text = "For you")
            }
            items(
                count = photos.itemCount,
                key = photos.itemKey { key -> key.id },
                contentType = photos.itemContentType { contentType -> contentType.id }
            ) { item ->
                photos[item]?.let { photos ->
                    PhotoItem(
                        imageRegular = photos.regular,
                        name = photos.name,
                        profileImageSmall = photos.profileImageLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun TopTextItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// Component for the normal grid.
@Composable
private fun PhotoItem(
    modifier: Modifier = Modifier,
    imageRegular: String,
    name: String,
    profileImageSmall: String
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
                    .size(40.dp),
                model = profileImageSmall,
                contentDescription = name
            )
            Text(
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
        AsyncImage(
            modifier = modifier
                .clip(RoundedCornerShape(3)),
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
            profileImageSmall = "image"
        )
    }
}