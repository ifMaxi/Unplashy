package com.maxidev.unplashy.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.maxidev.unplashy.R
import com.maxidev.unplashy.ui.theme.UnplashyTheme

@Composable
fun IconButtonItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes contentDescription: Int,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(contentDescription),
            //tint = if (isSystemInDarkTheme()) Color.Black else Color.White
        )
    }
}

@Preview
@Composable
private fun IconButtonItemPreview() {
    UnplashyTheme {
        IconButtonItem(
            icon = Icons.Default.Download,
            onClick = {},
            contentDescription = R.string.app_name
        )
    }
}