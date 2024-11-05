package com.maxidev.unplashy.ui.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maxidev.unplashy.R
import com.maxidev.unplashy.navigation.SettingsScreen
import com.maxidev.unplashy.ui.components.MediumTopBarItem
import com.maxidev.unplashy.ui.theme.UnplashyTheme
import com.maxidev.unplashy.ui.theme.montserratFamily

fun NavGraphBuilder.settingsScreen(
    toggle: Boolean,
    onToggleChange: (Boolean) -> Unit
) {
    composable<SettingsScreen> {

        SettingsView(
            toggle = toggle,
            onToggleChange = onToggleChange
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsView(
    modifier: Modifier = Modifier,
    toggle: Boolean,
    onToggleChange: (Boolean) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopBarItem(
                title = R.string.settings,
                scrollBehavior = scrollBehavior
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SettingsItem(
                    setting = R.string.dark_mode,
                    toggle = toggle,
                    onToggleChange = onToggleChange,
                    icon = if (!toggle) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDes = if (!toggle) R.string.light_mode else R.string.dark_mode
                )
            }
        }
    )
}

@Composable
private fun SettingsItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    @StringRes setting: Int,
    @StringRes contentDes: Int,
    toggle: Boolean,
    onToggleChange: (Boolean) -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(10)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = contentDes)
            )
            Text(
                text = stringResource(id = setting),
                fontFamily = montserratFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = toggle,
                onCheckedChange = onToggleChange,
                modifier = Modifier
                    .semantics { contentDescription = "Switch" }
            )
        }
    }
}

@Preview
@Composable
private fun SettingsItemPreview() {
    UnplashyTheme {
        var toggle by remember { mutableStateOf(false) }

        SettingsItem(
            toggle = toggle,
            onToggleChange = { toggle = it },
            setting = R.string.settings,
            icon = Icons.Default.LightMode,
            contentDes = R.string.dark_mode
        )
    }
}