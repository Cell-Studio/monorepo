package com.cellstudio.qrscanner.ui.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.view.Surface
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cellstudio.ads.ui.AdMobBanner
import com.cellstudio.qrscanner.ui.R
import com.cellstudio.qrscanner.ui.analyzer.BarcodeAnalyzer
import com.cellstudio.qrscanner.ui.services.LocalQrScannerUIService
import com.cellstudio.qrscanner.ui.ui.common.QrScannerView
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CameraScreen(
    onSettingsClicked: () -> Unit,
    onResult: (String) -> Unit
) {
    val vm = hiltViewModel<CameraViewModel>()
    val context = LocalContext.current
    val qrScannerUIService = LocalQrScannerUIService.current

    val uiState by vm.cameraState.collectAsStateWithLifecycle()

    val cameraPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { access -> vm.onAccessUpdated(access) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            vm.onImageUpload(it)
        }
    }

    LaunchedEffect(cameraPermission) {
        vm.onAccessUpdated(cameraPermission)
        if (!cameraPermission) {
            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    LaunchedEffect(key1 = vm.onResult) {
        vm.onResult.collectLatest {
            onResult(it)
        }
    }

    LaunchedEffect(key1 = vm.onSettingsClicked) {
        vm.onSettingsClicked.collectLatest {
            onSettingsClicked()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        QrScannerView(
            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetRotation(Surface.ROTATION_0)
                .build(),
            analyzer = BarcodeAnalyzer(
                onSuccess = {
                    vm.onResult(it)
                },
                onError = {
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            )
        )
        Box(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.TopEnd),
                onClick = {
                    vm.onSettingsClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = Color(0xFFD3D3D3)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                if (!uiState) {
                    OutlinedButton(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        onClick = {
                            vm.onProvideAccessClicked()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.qrscanner_camera_go_to_settings))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
                OutlinedButton(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    onClick = { galleryLauncher.launch(MIMETYPE_IMAGE) }
                ) {
                    Text(text = stringResource(id = R.string.qrscanner_camera_upload_from_gallery))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Icon(
                    modifier = Modifier
                        .size(56.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(R.drawable.double_circle_24dp),
                    contentDescription = null,
                    tint =  Color(0xFFD3D3D3)
                )
                qrScannerUIService.getResultBannerAdId()?.let {
                    Spacer(modifier = Modifier.height(4.dp))
                    AdMobBanner(
                        adId = it
                    )
                }
            }
        }
    }
}

internal const val MIMETYPE_IMAGE = "image/*"