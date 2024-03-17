package com.cellstudio.qrscanner.ui.ui.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.cellstudio.qrscanner.ui.R
import com.cellstudio.ui.appbar.AppBar
import com.cellstudio.ui.composition.LocalBrowserService
import com.cellstudio.ui.theme.grid_x2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBackPressed: () -> Unit,
) {
    val browserService = LocalBrowserService.current

    BackHandler { onBackPressed() }

    Scaffold(
        topBar = {
            AppBar(
                title = stringResource(id = R.string.qrscanner_settings_title),
                onBackPressed = onBackPressed
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            Column(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { browserService.openChromeTab("https://github.com/Cell-Studio/privacy-policy/blob/main/QRScanner/privacy-policy.md") }
                        .padding(grid_x2),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(grid_x2)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = stringResource(id = R.string.qrscanner_settings_privacy_policy),
                    )
                }
            }
            Column(
                Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { browserService.openChromeTab("https://github.com/Cell-Studio/privacy-policy/blob/main/QRScanner/terms-and-conditions.md") }
                        .padding(grid_x2),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(grid_x2)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = stringResource(id = R.string.qrscanner_settings_terms_and_conditions),
                    )
                }
            }
        }
    }
}