package com.maxidev.unplashy.ui.random

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.maxidev.unplashy.broadcast.AndroidDownloader
import com.maxidev.unplashy.domain.model.RandomPhoto
import com.maxidev.unplashy.ui.theme.UnplashyTheme
import com.maxidev.unplashy.utils.toastUtil

private const val UNKNOWN = "Unknown"

@Composable
fun RandomPhotoView(
    modifier: Modifier = Modifier,
    viewModel: RandomViewModel = hiltViewModel()
) {
    val state by viewModel.loadState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LoadStatus(
        modifier = modifier,
        status = state,
        scrollState = scrollState
    )
}

@Composable
private fun LoadStatus(
    modifier: Modifier = Modifier,
    status: RandomStatus,
    scrollState: ScrollState
) {

    when (status) {
        is RandomStatus.Error -> Text("error")
        is RandomStatus.Success -> {
            ContentView(
                modifier = modifier,
                randomPhoto = status.onSuccess ?: return,
                scrollState = scrollState
            )
        }
    }
}

@Composable
private fun ContentView(
    modifier: Modifier = Modifier,
    randomPhoto: RandomPhoto,
    scrollState: ScrollState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoWithLocation(
            description = randomPhoto.description,
            fullImageUrl = randomPhoto.fullImageUrl,
            city = randomPhoto.city,
            country = randomPhoto.country
        )
        UserPhotoWithDescription(
            name = randomPhoto.name,
            slug = randomPhoto.slug,
            profileImageUrl = randomPhoto.profileImageUrl,
            altDescription = randomPhoto.altDescription,
            html = randomPhoto.html,
            download = randomPhoto.download
        )
        HorizontalDivider()
        PhotoAndCameraInformation(
            width = randomPhoto.width,
            height = randomPhoto.height,
            model = randomPhoto.model,
            make = randomPhoto.make,
            exposureTime = randomPhoto.exposureTime,
            aperture = randomPhoto.aperture,
            focalLength = randomPhoto.focalLength,
            iso = randomPhoto.iso
        )
        HorizontalDivider()
        PhotoTagsItems(title = randomPhoto.title)
    }
}

@Composable
private fun PhotoWithLocation(
    modifier: Modifier = Modifier,
    description: String,
    fullImageUrl: String,
    city: String,
    country: String
) {
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
                .height(400.dp)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
        )
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location"
            )
            Text(
                text = if (city.isEmpty() || country.isEmpty()) UNKNOWN else "($city, $country)",
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun UserPhotoWithDescription(
    modifier: Modifier = Modifier,
    name: String,
    slug: String,
    profileImageUrl: String,
    altDescription: String,
    html: String,
    download: String
) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
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

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp),
                model = profileImageUrl,
                contentDescription = name
            )
            Text(
                text = name,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            DropMenuItem(
                expanded = expanded,
                onExpanded = { expanded = true },
                onDismissRequest = { expanded = false },
                onOpenBrowser = {
                    startActivity(context, browserIntent, null)
                    expanded = false
                },
                onDowLoad = {
                    downloadImage.download(url = download, nameFile = slug)
                    toastUtil(context, "Downloading").show()
                    expanded = false
                },
                onShare = {
                    startActivity(context, sendChooser, null)
                    expanded = false
                }
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
        )
    }
}

@Composable
private fun PhotoAndCameraInformation(
    modifier: Modifier = Modifier,
    width: Int,
    height: Int,
    model: String,
    make: String,
    exposureTime: String,
    aperture: String,
    focalLength: String,
    iso: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            InfoText(
                label = "Dimensions",
                value = if (width == 0 || height == 0) UNKNOWN else {
                    "${width}x${height}"
                }
            )
            InfoText(
                label = "Aperture",
                value = if (aperture.isEmpty()) UNKNOWN else "f/$aperture"
            )
            InfoText(
                label = "Shutter Speed",
                value = if (exposureTime.isEmpty()) UNKNOWN else "${exposureTime}s"
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            InfoText(
                label = "Camera",
                value = if (model.isEmpty() || make.isEmpty()) UNKNOWN else "$model, $make"
            )
            InfoText(
                label = "Focal Length",
                value = if (focalLength.isEmpty()) UNKNOWN else "${focalLength}mm"
            )
            InfoText(
                label = "ISO",
                value = if (iso == 0) UNKNOWN else iso.toString()
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun PhotoTagsItems(
    modifier: Modifier = Modifier,
    title: List<String>
) {
    FlowRow(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(10.dp),
        maxItemsInEachRow = 5,
        overflow = FlowRowOverflow.Visible,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        title.sorted()
            .forEach { tag ->
                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(20)
                        )
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = tag,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center
                    )
                }
            }
    }
}

@Composable
private fun InfoText(label: String, value: String) {
    val textBuilder = buildAnnotatedString {
        withStyle(
            style = SpanStyle(fontSize = 14.sp),
            block = { append(label) }
        )
        append("\n")
        withStyle(
            style = SpanStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            ),
            block = { append(value) }
        )
    }

    Text(
        text = textBuilder
    )
}

@Composable
private fun DropMenuItem(
    modifier: Modifier = Modifier,
    expanded: Boolean,
    onExpanded: () -> Unit,
    onDismissRequest: () -> Unit,
    onOpenBrowser: () -> Unit,
    onDowLoad: () -> Unit,
    onShare: () -> Unit
) {
    Box(
        modifier = modifier.wrapContentSize()
    ) {
        IconButton(onClick = onExpanded) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Options menu.",
            )
        }
        DropdownMenu(expanded = expanded, onDismissRequest = onDismissRequest) {
            DropdownMenuItem(
                text = { Text("Open in browser") },
                onClick = onOpenBrowser,
                leadingIcon = { Icon(Icons.Default.OpenInBrowser, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Download") },
                onClick = onDowLoad,
                leadingIcon = { Icon(Icons.Default.Download, contentDescription = null) }
            )
            DropdownMenuItem(
                text = { Text("Share") },
                onClick = onShare,
                leadingIcon = { Icon(Icons.Default.Share, contentDescription = null) }
            )
        }

    }
}

@Preview
@Composable
private fun ContentViewPreview() {
    UnplashyTheme {
        ContentView(
            randomPhoto = RandomPhoto(
                id = "",
                description = "Alone.",
                slug = "slug",
                width = 4082,
                height = 6334,
                altDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
                fullImageUrl = "image",
                html = "url",
                download = "url",
                name = "Joan Larsen",
                profileImageUrl = "image",
                make = "maker",
                model = "model",
                exposureTime = "exposure",
                aperture = "aperture",
                focalLength = "focal",
                iso = 4158,
                city = "Some city",
                country = "Some country",
                type = "type",
                title = listOf()
            ),
            scrollState = rememberScrollState()
        )
    }
}

@Preview
@Composable
private fun PhotoWithLocationAndUserDescriptionPreview() {
    UnplashyTheme {
        PhotoWithLocation(
            description = "Alone.",
            fullImageUrl = "image",
            city = "Chore",
            country = "Senegal"
        )
    }
}

@Preview
@Composable
private fun UserPhotoWithDescriptionPreview() {
    UnplashyTheme {
        UserPhotoWithDescription(
            name = "Myrtle Schneider",
            profileImageUrl = "image",
            altDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            html = "url",
            download = "url",
            slug = "slug"
        )
    }
}

@Preview
@Composable
private fun PhotoAndCameraInformationPreview() {
    UnplashyTheme {
        PhotoAndCameraInformation(
            width = 0,
            height = 0,
            model = "Camera model",
            make = "Make",
            exposureTime = "Exposure",
            aperture = "Aperture",
            focalLength = "Focal",
            iso = 1268
        )
    }
}

@Preview
@Composable
private fun PhotoTagsItemsPreview() {
    UnplashyTheme {
        PhotoTagsItems(
            title = listOf("title1", "title2", "title3", "title4", "title5", "title6", "title7")
        )
    }
}