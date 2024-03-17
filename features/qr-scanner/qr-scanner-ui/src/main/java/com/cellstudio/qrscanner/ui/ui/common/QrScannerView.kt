package com.cellstudio.qrscanner.ui.ui.common

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun QrScannerView(
    imageCapture: ImageCapture,
    analyzer: ImageAnalysis.Analyzer,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        CameraView(
            modifier = Modifier.fillMaxSize(),
            imageCapture = imageCapture,
            analyzer = analyzer
        )

        QrOverlay()
    }
}