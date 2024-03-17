package com.cellstudio.core.di.modules

import android.content.Context
import com.cellstudio.core.clipboard.ClipboardService
import com.cellstudio.core.clipboard.ClipboardServiceFacade
import com.cellstudio.core.di.qualifiers.CellDispatchers
import com.cellstudio.core.di.qualifiers.Dispatcher
import com.cellstudio.core.pm.PackageManager
import com.cellstudio.core.pm.PackageManagerFacade
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CommonModule {
    @Provides
    @Singleton
    @Dispatcher(dispatcher = CellDispatchers.DEFAULT)
    fun providesDefaultDispatcher(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    @Provides
    @Singleton
    @Dispatcher(dispatcher = CellDispatchers.MAIN)
    fun providesMainDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Provides
    @Singleton
    @Dispatcher(dispatcher = CellDispatchers.IO)
    fun providesIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun providesClipboardService(@ApplicationContext appContext: Context): ClipboardService {
        return ClipboardServiceFacade(appContext)
    }

    @Provides
    @Singleton
    fun providesPackageManager(@ApplicationContext appContext: Context): PackageManager {
        return PackageManagerFacade(appContext)
    }
}