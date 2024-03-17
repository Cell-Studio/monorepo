package com.cellstudio.tcg.domain.usecases

import com.cellstudio.tcg.domain.models.PokemonCard
import com.cellstudio.tcg.domain.repositories.TcgRepository
import javax.inject.Inject

interface GetPokemonCardListUseCase {
    suspend fun execute(query: String): List<PokemonCard>
}

internal class GetPokemonCardListUseCaseImpl @Inject constructor(private val tcgRepository: TcgRepository):
    GetPokemonCardListUseCase {
    override suspend fun execute(query: String): List<PokemonCard> {
        return tcgRepository.getPokemonCardList(query)
    }
}