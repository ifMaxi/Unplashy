package com.maxidev.unplashy.ui.details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.maxidev.unplashy.R
import com.maxidev.unplashy.ui.theme.UnplashyTheme

@Composable
fun HeadPhotoItem(
    modifier: Modifier = Modifier,
    description: String,
    fullImageUrl: String,
    width: Int,
    height: Int,
    city: String,
    country: String,
    navigateToImageZoom: (String, Float, Float) -> Unit
) {
    val textBuilder = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = 24.sp,
                shadow = Shadow(color = Color.Black, blurRadius = 8f)
            ),
            block = { if (city.isNotEmpty()) append("$city, ") },
        )
        withStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = 24.sp,
                shadow = Shadow(color = Color.Black, blurRadius = 8f)
            ),
            block = { if (country.isNotEmpty()) append(country) }
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = fullImageUrl,
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .clickable {
                    navigateToImageZoom(fullImageUrl, width.toFloat(), height.toFloat())
                }
        )
        if (textBuilder.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.BottomStart),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(R.string.location),
                    tint = if (isSystemInDarkTheme()) Color.Black else Color.White,
                    modifier = Modifier.shadow(elevation = 10.dp)
                )
                Text(
                    text = textBuilder,
                    modifier = Modifier
                        .semantics { contentDescription = textBuilder.text }
                )
            }
        }
    }
}

@Preview
@Composable
private fun HeadPhotoItemPreview() {
    UnplashyTheme {
        HeadPhotoItem(
            description = "sumo",
            fullImageUrl = "http://www.bing.com/search?q=bibendum",
            width = 2418,
            height = 5231,
            city = "New York",
            country = "United States",
            navigateToImageZoom = { _: String, _: Float, _: Float -> }
        )
    }
}