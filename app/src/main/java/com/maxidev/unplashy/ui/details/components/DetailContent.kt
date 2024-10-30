package com.maxidev.unplashy.ui.details.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maxidev.unplashy.domain.model.PhotoId

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    detail: PhotoId,
    scrollState: ScrollState,
    navigateToImageZoom: (String, Float, Float) -> Unit,
    navigateBack: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            ActionsBarItem(
                slug = detail.slug,
                download = detail.downloadUrl,
                html = detail.htmlUrl,
                navigateBack = navigateBack
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .consumeWindowInsets(innerPadding)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeadPhotoItem(
                    description = detail.description,
                    fullImageUrl = detail.fullUrl,
                    width = detail.width,
                    height = detail.height,
                    city = detail.city,
                    country = detail.country,
                    navigateToImageZoom = navigateToImageZoom
                )
                UserPhotoWithData(
                    name = detail.name,
                    profileImageUrl = detail.profileImageUrl,
                    altDescription = detail.altDescription
                )
                HorizontalDivider()
                PhotoInformation(
                    width = detail.width,
                    height = detail.height,
                    model = detail.model,
                    make = detail.make,
                    exposureTime = detail.exposureTime,
                    aperture = detail.aperture,
                    focalLength = detail.focalLength,
                    iso = detail.iso,
                    createdAt = detail.createdAt
                )
                HorizontalDivider()
                TagsItem(title = detail.title)
            }
        }
    )
}