package com.cellstudio.core.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import javax.inject.Inject

interface ClipboardService {
    fun putInto(data: String)
    fun getFrom(): String
}

internal class ClipboardServiceFacade @Inject constructor(
    private val context: Context
) : ClipboardService {

    private val clipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    override fun putInto(data: String) {
        clipboardManager.setPrimaryClip(ClipData.newPlainText(CLIP_INFO, data))
    }

    override fun getFrom(): String {
        return clipboardManager.primaryClip?.getItemAt(0)?.text?.toString().orEmpty()
    }
}

private const val CLIP_INFO = "clip_info"

