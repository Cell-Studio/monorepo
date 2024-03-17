package com.cellstudio.vgc.domain.di

import com.cellstudio.vgc.domain.usecases.GetMoveDetailsUseCase
import com.cellstudio.vgc.domain.usecases.GetMoveDetailsUseCaseImpl
import com.cellstudio.vgc.domain.usecases.GetMoveListUseCase
import com.cellstudio.vgc.domain.usecases.GetMoveListUseCaseImpl
import com.cellstudio.vgc.domain.usecases.GetPokemonDetailsUseCase
import com.cellstudio.vgc.domain.usecases.GetPokemonDetailsUseCaseImpl
import com.cellstudio.vgc.domain.usecases.GetPokemonListUseCase
import com.cellstudio.vgc.domain.usecases.GetPokemonListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface VgcDomainModule {
    @Binds
    fun bindGetPokemonListUseCase(getPokemonListUseCase: GetPokemonListUseCaseImpl): GetPokemonListUseCase

    @Binds
    fun bindGetPokemonDetailsUseCase(getPokemonDetailsUseCase: GetPokemonDetailsUseCaseImpl): GetPokemonDetailsUseCase

    @Binds
    fun bindGetMoveListUseCase(getMoveListUseCase: GetMoveListUseCaseImpl): GetMoveListUseCase

    @Binds
    fun bindGetMoveDetailsUseCase(getMoveDetailsUseCase: GetMoveDetailsUseCaseImpl): GetMoveDetailsUseCase
}