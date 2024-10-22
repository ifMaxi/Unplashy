package com.maxidev.unplashy.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil3.compose.AsyncImage
import com.maxidev.unplashy.domain.model.Search
import com.maxidev.unplashy.navigation.SearchScreen
import com.maxidev.unplashy.ui.components.SearchBarItem
import kotlinx.coroutines.launch

fun NavGraphBuilder.searchPhotoScreen() {
    composable<SearchScreen> {
        val viewModel = hiltViewModel<SearchViewModel>()
        val state = viewModel.searchPhotos.collectAsLazyPagingItems()
        val lazyState = rememberLazyStaggeredGridState()
        val query by viewModel.query
        var isExpanded by rememberSaveable { mutableStateOf(false) }
        val scope = rememberCoroutineScope()
        val focusManager = LocalFocusManager.current

        SearchPhotoView(
            photoPaging = state,
            lazyState = lazyState,
            query = query,
            expanded = isExpanded,
            onQueryChange = viewModel::onQueryChange,
            onExpandedChange = { isExpanded = false },
            onSearch = {
                scope.launch {
                    viewModel.searchData(it)
                }
                isExpanded = false
                focusManager.clearFocus()
            },
            onClear = {
                viewModel.clearText()
                focusManager.clearFocus()
            }
        )
    }
}

@Composable
fun SearchPhotoView(
    modifier: Modifier = Modifier,
    photoPaging: LazyPagingItems<Search>,
    lazyState: LazyStaggeredGridState,
    query: String,
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit
) {
    val photoState = remember(photoPaging) { photoPaging }

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBarItem(
                query = query,
                expanded = expanded,
                onQueryChange = onQueryChange,
                onExpandedChange = onExpandedChange,
                onSearch = onSearch,
                onClear = onClear
            )
        }
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Adaptive(150.dp),
            state = lazyState,
            contentPadding = innerPadding,
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                items(
                    count = photoState.itemCount,
                    key = photoState.itemKey { key -> key.id },
                    contentType = photoState.itemContentType { contentType -> contentType.id }
                ) { index ->
                    photoState[index]?.let { photo ->
                        PhotoItem(
                            image = photo.regularImage
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun PhotoItem(
    modifier: Modifier = Modifier,
    image: String
) {
    Box(
        modifier = modifier.wrapContentSize(),
        content = {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(3))
            )
        }
    )
}