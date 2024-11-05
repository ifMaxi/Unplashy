package com.maxidev.unplashy.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.unplashy.R
import com.maxidev.unplashy.ui.theme.UnplashyTheme
import com.maxidev.unplashy.ui.theme.montserratFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarItem(
    modifier: Modifier = Modifier,
    query: String,
    expanded: Boolean,
    onQueryChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSearch: (String) -> Unit,
    onClear: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = query,
                    onQueryChange = onQueryChange,
                    onSearch = onSearch,
                    expanded = expanded,
                    onExpandedChange = onExpandedChange,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search_placeholder),
                            fontFamily = montserratFamily
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_placeholder)
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty() || query.isNotBlank()) {
                            IconButton(onClick = onClear) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )
            },
            windowInsets = WindowInsets.ime,
            expanded = expanded,
            onExpandedChange = onExpandedChange,
            shape = RoundedCornerShape(10),
            content = { /* Do nothing. */ }
        )
    }
}

@Preview
@Composable
private fun SearchBarItemPreview() {
    UnplashyTheme {
        SearchBarItem(
            query = "",
            onQueryChange = {},
            expanded = false,
            onExpandedChange = {},
            onSearch = {},
            onClear = {}
        )
    }
}