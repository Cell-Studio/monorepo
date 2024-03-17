package com.cellstudio.qrscanner.ui.ui.results

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cellstudio.ads.ui.AdMobBanner
import com.cellstudio.qrscanner.ui.R
import com.cellstudio.qrscanner.ui.services.LocalQrScannerUIService
import com.cellstudio.qrscanner.ui.ui.common.ReadTextField
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    result: String,
    onBackPressed: () -> Unit,
) {
    val vm = hiltViewModel<ResultsViewModel>()
    val uiState by vm.resultState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val qrScannerUIService = LocalQrScannerUIService.current
    BackHandler { onBackPressed() }

    LaunchedEffect(key1 = result) {
        vm.onUpdateResult(result)
    }

    LaunchedEffect(key1 = vm.showToastMessage) {
        vm.showToastMessage.collectLatest {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.qrscanner_results_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            Column {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .defaultMinSize(minHeight = 56.dp)
                        .fillMaxWidth(),
                    onClick = { vm.onOpenWebsite() }
                ) {
                    Text(stringResource(id = R.string.qrscanner_result_open_website))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Button(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .defaultMinSize(minHeight = 56.dp)
                        .fillMaxWidth(),
                    onClick = { vm.onCopyText() }
                ) {
                    Text(stringResource(id = R.string.qrscanner_result_copy_to_clipboard))
                }
                qrScannerUIService.getResultBannerAdId()?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    AdMobBanner(
                        adId = it
                    )
                }
            }

        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ReadTextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .defaultMinSize(minHeight = 240.dp)
                    .fillMaxWidth(),
                label = {
                    Text(text = stringResource(id = R.string.qrscanner_results_title))
                },
                value = uiState,
            )
        }
    }
}