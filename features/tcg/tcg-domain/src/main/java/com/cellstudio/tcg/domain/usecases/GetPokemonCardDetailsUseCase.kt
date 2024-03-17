package com.cellstudio.tcg.domain.usecases

import com.cellstudio.tcg.domain.models.PokemonCard
import com.cellstudio.tcg.domain.models.PokemonCardDetails
import com.cellstudio.tcg.domain.repositories.TcgRepository
import javax.inject.Inject

interface GetPokemonCardDetailsUseCase {
    suspend fun execute(id: String):PokemonCardDetails
}

internal class GetPokemonCardDetailsUseCaseImpl @Inject constructor(private val tcgRepository: TcgRepository):
    GetPokemonCardDetailsUseCase {
    override suspend fun execute(id: String): PokemonCardDetails {
        return tcgRepository.getPokemonCardDetails(id)
    }
}