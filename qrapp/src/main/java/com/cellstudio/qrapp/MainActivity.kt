package com.cellstudio.qrapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.cellstudio.ads.service.AdsService
import com.cellstudio.core.browser.BrowserService
import com.cellstudio.qrapp.navigation.QrAppNavHost
import com.cellstudio.qrscanner.ui.services.LocalQrScannerUIService
import com.cellstudio.qrscanner.ui.services.QrScannerUIService
import com.cellstudio.ui.composition.LocalBrowserService
import com.cellstudio.ui.theme.CellTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var browserService: BrowserService

    @Inject
    lateinit var adsService: AdsService

    @Inject
    lateinit var qrScannerUIService: QrScannerUIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT))
        adsService.initialize()

        setContent {
            CompositionLocalProvider(LocalBrowserService provides browserService) {
                CompositionLocalProvider(LocalQrScannerUIService provides qrScannerUIService) {
                    CellTheme {
                        QrAppNavHost()
                    }
                }
            }
        }
    }
}