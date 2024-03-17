package com.cellstudio.vgc.domain.usecases

import com.cellstudio.vgc.domain.models.Pokemon
import com.cellstudio.vgc.domain.repositories.VgcRepository
import javax.inject.Inject

interface GetPokemonListUseCase {
    suspend fun execute(name: String?): List<Pokemon>
}

internal class GetPokemonListUseCaseImpl @Inject constructor(private val vgcRepository: VgcRepository): GetPokemonListUseCase {
    override suspend fun execute(name: String?): List<Pokemon> {
        return vgcRepository.getPokemonList(name)
    }
}