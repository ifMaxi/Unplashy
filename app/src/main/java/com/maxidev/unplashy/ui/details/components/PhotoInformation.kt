package com.maxidev.unplashy.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.unplashy.ui.theme.UnplashyTheme
import com.maxidev.unplashy.utils.DateTimeUtils.dateTime
import com.maxidev.unplashy.utils.DateTimeUtils.formatter

private const val UNKNOWN = "Unknown"

@Composable
fun PhotoInformation(
    modifier: Modifier = Modifier,
    width: Int,
    height: Int,
    model: String,
    make: String,
    exposureTime: String,
    aperture: String,
    focalLength: String,
    iso: Int,
    createdAt: String
) {
    val formattedCreatedAt = dateTime(createdAt).format(formatter)

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
                label = "Created At",
                value = formattedCreatedAt.ifEmpty { UNKNOWN }
            )
            InfoText(
                label = "Aperture",
                value = if (aperture.isEmpty()) UNKNOWN else "f/$aperture",
                fontStyle = FontStyle.Italic
            )
            InfoText(
                label = "Shutter Speed",
                value = if (exposureTime.isEmpty()) UNKNOWN else "${exposureTime}s",
                fontStyle = FontStyle.Italic
            )
        }
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

@Composable
private fun InfoText(
    label: String,
    value: String,
    fontStyle: FontStyle = FontStyle.Normal
) {
    val textBuilder = buildAnnotatedString {
        withStyle(
            style = SpanStyle(fontSize = 14.sp),
            block = { append(label) }
        )
        append("\n")
        withStyle(
            style = SpanStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                fontStyle = fontStyle
            ),
            block = { append(value) }
        )
    }

    Text(text = textBuilder)
}

@Preview
@Composable
private fun PhotoInformationPreview() {
    UnplashyTheme {
        PhotoInformation(
            width = 7786,
            height = 9013,
            model = "saperet",
            make = "doming",
            exposureTime = "sale",
            aperture = "maximus",
            focalLength = "turpis",
            iso = 5255,
            createdAt = "eos"
        )
    }
}