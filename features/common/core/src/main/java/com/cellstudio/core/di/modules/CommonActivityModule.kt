package com.cellstudio.core.di.modules

import com.cellstudio.core.browser.BrowserService
import com.cellstudio.core.browser.BrowserServiceFacade
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal interface CommonActivityModule {
    @Binds
    fun bindBrowserService(browserService: BrowserServiceFacade): BrowserService
}