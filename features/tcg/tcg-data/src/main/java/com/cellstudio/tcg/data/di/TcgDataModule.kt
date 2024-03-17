package com.cellstudio.tcg.data.di

import com.cellstudio.tcg.data.repositories.TcgRepositoryImpl
import com.cellstudio.tcg.domain.repositories.TcgRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TcgDataModule {
    @Binds
    fun bindTcgRepository(tcgRepository: TcgRepositoryImpl): TcgRepository
}