package com.cellstudio.tcg.domain.repositories

import com.cellstudio.tcg.domain.models.PokemonCard
import com.cellstudio.tcg.domain.models.PokemonCardDetails

interface TcgRepository {
    suspend fun getPokemonCardList(query: String): List<PokemonCard>
    suspend fun getPokemonCardDetails(id: String): PokemonCardDetails
}