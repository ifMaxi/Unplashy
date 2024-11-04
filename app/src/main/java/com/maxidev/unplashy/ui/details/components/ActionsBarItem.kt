package com.maxidev.unplashy.ui.details.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maxidev.unplashy.R
import com.maxidev.unplashy.broadcast.AndroidDownloader
import com.maxidev.unplashy.ui.components.IconButtonItem
import com.maxidev.unplashy.ui.theme.UnplashyTheme
import com.maxidev.unplashy.utils.toastUtil

@Composable
fun ActionsBarItem(
    modifier: Modifier = Modifier,
    slug: String,
    download: String,
    html: String,
    navigateBack: () -> Unit
) {
    val context = LocalContext.current
    val downloadImage = AndroidDownloader(context)
    val sendIntent = Intent.ACTION_SEND
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(html))
    val sendChooser = Intent.createChooser(
        Intent().apply {
            action = sendIntent
            putExtra(Intent.EXTRA_TEXT, html)
            type = "text/plain"
        },
        "Share image link."
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .height(110.dp)
            .statusBarsPadding()
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(color = MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        IconButtonItem(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = R.string.navigate_back,
            onClick = navigateBack
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButtonItem(
            icon = Icons.Default.Download,
            contentDescription = R.string.download,
            onClick = {
                downloadImage.download(url = download, nameFile = slug)
                toastUtil(context, "Downloading").show()
            }
        )
        IconButtonItem(
            icon = Icons.Default.OpenInBrowser,
            contentDescription = R.string.open_in_browser,
            onClick = { context.startActivity(browserIntent, null) }
        )
        IconButtonItem(
            icon = Icons.Default.Share,
            contentDescription = R.string.share,
            onClick = { context.startActivity(sendChooser, null) }
        )
    }
}

@Preview
@Composable
private fun ActionsBarItemPreview() {
    UnplashyTheme {
        ActionsBarItem(
            slug = "hinc",
            download = "perpetua",
            html = "definitionem",
            navigateBack = {}
        )
    }
}