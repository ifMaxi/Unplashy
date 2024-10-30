package com.maxidev.unplashy.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun PhotoItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    navigateToDetail: () -> Unit
) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4))
            .clickable { navigateToDetail() },
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )
}
