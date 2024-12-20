package com.maxidev.unplashy.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.unplashy.R
import com.maxidev.unplashy.ui.theme.UnplashyTheme

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    settingsIcon: ImageVector,
    searchIcon: ImageVector,
    topicsIcon: ImageVector,
    fabIcon: ImageVector,
    navigateToSettings: () -> Unit,
    navigateToMainPage: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToTopics: () -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
        actions = {
            IconButton(onClick = navigateToSettings) {
                Icon(
                    imageVector = settingsIcon,
                    contentDescription = stringResource(R.string.settings)
                )
            }
            IconButton(onClick = navigateToSearch) {
                Icon(
                    imageVector = searchIcon,
                    contentDescription = stringResource(R.string.search)
                )
            }
            IconButton(onClick = navigateToTopics) {
                Icon(
                    imageVector = topicsIcon,
                    contentDescription = stringResource(R.string.topics)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToMainPage,
                shape = RoundedCornerShape(15),
                elevation = FloatingActionButtonDefaults.elevation(6.dp),
                content = {
                    Icon(
                        imageVector = fabIcon,
                        contentDescription = null
                    )
                }
            )
        }
    )
}

@Preview
@Composable
private fun BottomBarItemPreview() {
    UnplashyTheme {
        BottomBarItem(
            topicsIcon = Icons.Default.Collections,
            fabIcon = Icons.Default.Image,
            searchIcon = Icons.Default.Search,
            settingsIcon = Icons.Default.Settings,
            navigateToMainPage = {},
            navigateToSearch = {},
            navigateToTopics = {},
            navigateToSettings = {}
        )
    }
}