package com.maxidev.unplashy.ui.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.maxidev.unplashy.ui.theme.UnplashyTheme

@Composable
fun UserPhotoWithData(
    modifier: Modifier = Modifier,
    name: String,
    profileImageUrl: String,
    altDescription: String,
    city: String,
    country: String
) {
    val textBuilder = buildAnnotatedString {
        withStyle(
            style = SpanStyle(fontSize = 14.sp),
            block = { if (city.isNotEmpty()) append("$city, ") },
        )
        withStyle(
            style = SpanStyle(fontSize = 14.sp),
            block = { if (country.isNotEmpty()) append(country) }
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
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
                .size(80.dp),
            model = profileImageUrl,
            contentDescription = name
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = name,
                fontSize = 18.sp,
                modifier = Modifier
                    .semantics { contentDescription = name }
            )
            if (textBuilder.isNotEmpty()) {
                Text(
                    text = textBuilder,
                    modifier = Modifier
                        .semantics { contentDescription = textBuilder.text }
                )
            }
            Text(
                text = altDescription,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .semantics { contentDescription = altDescription }
            )
        }
    }
}

@Preview
@Composable
private fun UserPhotoWithDataPreview() {
    UnplashyTheme {
        UserPhotoWithData(
            name = "Tabatha Villarreal",
            profileImageUrl = "https://search.yahoo.com/search?p=splendide",
            altDescription = "Lorem impsum dolor sit amet.",
            city = "Mclean",
            country = "Libya"
        )
    }
}