package com.cellstudio.qrscanner.ui.analyzer

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage
import javax.inject.Inject

internal interface BarcodeScanService {
    fun scanImage(
        imageUri: Uri,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    )
}

internal class BarcodeScanServiceImpl @Inject constructor(
    private val detector: BarcodeScanner,
    private val context: Context
): BarcodeScanService {
    override fun scanImage(
        imageUri: Uri,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        detector.process(
            InputImage.fromFilePath(context, imageUri)
        ).addOnSuccessListener { results ->
            results.firstOrNull()?.let {barcode ->
                barcode.rawValue?.let {
                    onSuccess(it)
                }
            }
        }.addOnFailureListener {
            Log.e(this.javaClass.simpleName, it.toString())
            onError(it)
        }

    }

}