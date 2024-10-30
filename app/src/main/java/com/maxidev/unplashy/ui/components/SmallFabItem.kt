package com.maxidev.unplashy.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.maxidev.unplashy.ui.theme.UnplashyTheme

@Composable
fun SmallFabItem(
    modifier: Modifier = Modifier,
    smallFabIcon: ImageVector,
    onClick: () -> Unit
) {
    SmallFloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(15),
        content = {
            Icon(
                imageVector = smallFabIcon,
                contentDescription = null
            )
        }
    )
}

@Preview
@Composable
private fun SmallFabItemPreview() {
    UnplashyTheme {
        SmallFabItem(
            smallFabIcon = Icons.Default.ArrowDropUp,
            onClick = {}
        )
    }
}