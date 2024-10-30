package com.maxidev.unplashy.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopBarItem(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    @DrawableRes image: Int?,
    scrollBehavior: TopAppBarScrollBehavior
) {
    MediumTopAppBar(
        modifier = modifier
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
        title = {
            Text(
                text = stringResource(title),
                fontSize = 36.sp,
                fontWeight = FontWeight.Medium
            )
        },
        navigationIcon = {
            image?.let { painterResource(it) }?.let {
                Image(
                    painter = it,
                    contentDescription = stringResource(title),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }
        },
        expandedHeight = 180.dp,
        scrollBehavior = scrollBehavior
    )
}