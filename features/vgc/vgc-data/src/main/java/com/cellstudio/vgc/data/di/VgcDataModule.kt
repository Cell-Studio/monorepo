package com.cellstudio.vgc.data.di

import com.cellstudio.vgc.data.repositories.VgcRepositoryImpl
import com.cellstudio.vgc.domain.repositories.VgcRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface VgcDataModule {
    @Binds
    fun bindVgcRepository(vgcRepository: VgcRepositoryImpl): VgcRepository
}