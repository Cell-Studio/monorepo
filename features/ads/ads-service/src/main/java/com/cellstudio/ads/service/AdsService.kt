package com.cellstudio.ads.service

import android.content.Context
import com.google.android.gms.ads.MobileAds
import javax.inject.Inject

interface AdsService {
    fun initialize()
}

internal class AdsServiceFacade @Inject constructor(private val context: Context): AdsService {

    override fun initialize() {
        MobileAds.initialize(context)
    }
}