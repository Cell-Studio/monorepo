package com.cellstudio.qrscanner.ui.services

import androidx.compose.runtime.staticCompositionLocalOf

val LocalQrScannerUIService = staticCompositionLocalOf<QrScannerUIService> {
    error("CompositionLocal LocalQrScannerUIService not present")
}

interface QrScannerUIService {
    fun initialize(
        homeBannerAdId: String? = null,
        resultBannerAdId: String? = null
    )

    fun getHomeBannerAdId(): String?
    fun getResultBannerAdId(): String?
}

internal class QrScannerUIServiceFacade : QrScannerUIService {
    private var homeBannerAdId: String?=null
    private var resultBannerAdId: String?=null

    override fun initialize(homeBannerAdId: String?, resultBannerAdId: String?) {
        this.homeBannerAdId = homeBannerAdId
        this.resultBannerAdId = resultBannerAdId
    }

    override fun getHomeBannerAdId(): String? {
        return homeBannerAdId
    }

    override fun getResultBannerAdId(): String? {
        return resultBannerAdId
    }
}

