package com.cellstudio.vgc.domain.usecases

import com.cellstudio.vgc.domain.models.PokemonDetails
import com.cellstudio.vgc.domain.repositories.VgcRepository
import javax.inject.Inject

interface GetPokemonDetailsUseCase {
    suspend fun execute(id: Int): PokemonDetails
}

internal class GetPokemonDetailsUseCaseImpl @Inject constructor(private val vgcRepository: VgcRepository):
    GetPokemonDetailsUseCase {
    override suspend fun execute(id: Int): PokemonDetails {
        return vgcRepository.getPokemonDetails(id)
    }
}