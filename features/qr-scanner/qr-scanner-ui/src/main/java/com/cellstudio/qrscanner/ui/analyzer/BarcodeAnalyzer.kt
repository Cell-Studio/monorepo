package com.cellstudio.qrscanner.ui.analyzer

import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

internal class BarcodeAnalyzer (
    private val onSuccess: (String) -> Unit,
    private val onError: (Throwable) -> Unit
): ImageAnalysis.Analyzer {
    private val detector = BarcodeScanning.getClient()

    @androidx.camera.core.ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image?: return

        detector.process(InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees))
            .addOnSuccessListener { results ->
                results.firstOrNull()?.let {barcode ->
                    barcode.rawValue?.let {
                        onSuccess(it)
                    }
                }
                imageProxy.close()
            }.addOnFailureListener {
                Log.e(this.javaClass.simpleName, it.toString())
                onError(it)
                imageProxy.close()
            }
    }
}