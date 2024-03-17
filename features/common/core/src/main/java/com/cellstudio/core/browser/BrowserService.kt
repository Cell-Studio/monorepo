package com.cellstudio.core.browser

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

interface BrowserService {
    fun openChromeTab(url: String)
}

internal class BrowserServiceFacade @Inject constructor(@ActivityContext private val context: Context): BrowserService {
    override fun openChromeTab(url: String) {
        val intent = CustomTabsIntent.Builder()
            .build()
        intent.launchUrl(context, Uri.parse(url))
    }
}