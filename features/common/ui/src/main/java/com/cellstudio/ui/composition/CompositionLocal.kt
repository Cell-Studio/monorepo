package com.cellstudio.ui.composition

import androidx.compose.runtime.staticCompositionLocalOf
import com.cellstudio.core.browser.BrowserService

val LocalBrowserService = staticCompositionLocalOf<BrowserService> {
    error("CompositionLocal LocalBrowserService not present")
}