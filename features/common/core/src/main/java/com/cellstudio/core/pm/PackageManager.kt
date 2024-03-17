package com.cellstudio.core.pm

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

interface PackageManager {
    fun openUniversalLink(url: String)
    fun openApplicationDetailsSettings()
}

internal class PackageManagerFacade(private val context: Context): PackageManager {
    override fun openUniversalLink(url: String) {
        context.startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        )
    }

    override fun openApplicationDetailsSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", context.packageName, null)
        intent.setData(uri)
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}