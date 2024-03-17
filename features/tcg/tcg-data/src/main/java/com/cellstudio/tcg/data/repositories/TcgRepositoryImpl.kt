package com.cellstudio.tcg.data.repositories

import com.cellstudio.tcg.data.apis.TcgApi
import com.cellstudio.tcg.domain.models.PokemonCard
import com.cellstudio.tcg.domain.models.PokemonCardDetails
import com.cellstudio.tcg.domain.repositories.TcgRepository
import javax.inject.Inject

internal class TcgRepositoryImpl @Inject constructor(
    private val tcgApi: TcgApi
): TcgRepository {
    override suspend fun getPokemonCardList(query: String): List<PokemonCard> {
        val response = tcgApi.search(query)
        return response.data.map { PokemonCard(it.id, it.name, it.images["small"] ?: "") }
    }

    override suspend fun getPokemonCardDetails(id: String): PokemonCardDetails {
        val response = tcgApi.getCardDetails(id)
        return PokemonCardDetails(response.data.id,response.data.name, response.data.images["small"]?: "")
    }
}