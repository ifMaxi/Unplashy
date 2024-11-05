package com.maxidev.unplashy.ui.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxidev.unplashy.ui.theme.UnplashyTheme
import com.maxidev.unplashy.ui.theme.montserratFamily

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsItem(
    modifier: Modifier = Modifier,
    title: List<String>
) {
    FlowRow(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.Center,
        overflow = FlowRowOverflow.Clip
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
                        fontFamily = montserratFamily,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .semantics { contentDescription = tag }
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
            }
    }
}

@Preview(showBackground = true)
@Composable
private fun PhotoTagsItemsPreview() {
    UnplashyTheme {
        TagsItem(
            title = listOf(
                "title1", "title2", "title3", "title4", "title5", "title6", "title7",
                "title8", "title9", "title10", "title11", "title12", "title13", "title14",
                "title15", "title16", "title17", "title18", "title19", "title20", "title21",
                "title22", "title23", "title24", "title25", "title26", "title27", "title28"
            )
        )
    }
}