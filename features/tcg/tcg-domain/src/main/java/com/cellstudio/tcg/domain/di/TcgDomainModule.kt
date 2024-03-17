package com.cellstudio.tcg.domain.di

import com.cellstudio.tcg.domain.usecases.GetPokemonCardDetailsUseCase
import com.cellstudio.tcg.domain.usecases.GetPokemonCardDetailsUseCaseImpl
import com.cellstudio.tcg.domain.usecases.GetPokemonCardListUseCase
import com.cellstudio.tcg.domain.usecases.GetPokemonCardListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TcgDomainModule {
    @Binds
    fun bindGetPokemonListUseCase(getPokemonListUseCase: GetPokemonCardListUseCaseImpl): GetPokemonCardListUseCase

    @Binds
    fun bindGetPokemonCardDetailsUseCase(getPokemonCardDetailsUseCase: GetPokemonCardDetailsUseCaseImpl): GetPokemonCardDetailsUseCase
}