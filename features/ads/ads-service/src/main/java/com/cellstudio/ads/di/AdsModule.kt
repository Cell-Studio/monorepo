package com.cellstudio.ads.di

import android.content.Context
import com.cellstudio.ads.service.AdsService
import com.cellstudio.ads.service.AdsServiceFacade
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AdsModule {
    @Provides
    @Singleton
    fun providesAdsService(@ApplicationContext appContext: Context): AdsService {
        return AdsServiceFacade(appContext)
    }
}