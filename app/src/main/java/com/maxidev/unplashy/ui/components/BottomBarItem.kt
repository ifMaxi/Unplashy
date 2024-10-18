package com.maxidev.unplashy.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.unplashy.ui.theme.UnplashyTheme

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    fabText: String,
    searchIcon: ImageVector,
    fabIcon: ImageVector,
    onFabClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .clip(
                RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ),
        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
        actions = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = searchIcon,
                    contentDescription = null
                )
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onFabClick,
                icon = {
                    Icon(
                        imageVector = fabIcon,
                        contentDescription = null
                    )
                },
                text = {
                    Text(
                        text = fabText
                    )
                },
                shape = RoundedCornerShape(15),
                elevation = FloatingActionButtonDefaults.elevation(6.dp)
            )
        }
    )
}

@Preview
@Composable
private fun BottomBarItemPreview() {
    UnplashyTheme {
        BottomBarItem(
            fabText = "Random",
            fabIcon = Icons.Default.Image,
            searchIcon = Icons.Default.Search,
            onFabClick = {},
            onSearchClick = {}
        )
    }
}