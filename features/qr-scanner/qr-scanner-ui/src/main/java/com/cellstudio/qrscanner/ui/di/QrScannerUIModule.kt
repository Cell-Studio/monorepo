package com.cellstudio.qrscanner.ui.di

import android.content.Context
import com.cellstudio.qrscanner.ui.analyzer.BarcodeScanService
import com.cellstudio.qrscanner.ui.analyzer.BarcodeScanServiceImpl
import com.cellstudio.qrscanner.ui.services.QrScannerUIService
import com.cellstudio.qrscanner.ui.services.QrScannerUIServiceFacade
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object QrScannerUIModule {

    @Singleton
    @Provides
    fun provideBarcodeScanService(@ApplicationContext appContext: Context, barcodeScanner: BarcodeScanner): BarcodeScanService {
        return BarcodeScanServiceImpl(barcodeScanner, appContext)
    }

    @Singleton
    @Provides
    fun provideBarcodeScanner(): BarcodeScanner {
        return BarcodeScanning.getClient()
    }

    @Singleton
    @Provides
    fun provideQrScannerUIService(): QrScannerUIService {
        return QrScannerUIServiceFacade()
    }
}