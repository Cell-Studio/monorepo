package com.cellstudio.vgc.domain.repositories

import com.cellstudio.vgc.domain.models.Move
import com.cellstudio.vgc.domain.models.MoveDetails
import com.cellstudio.vgc.domain.models.Pokemon
import com.cellstudio.vgc.domain.models.PokemonDetails

interface VgcRepository {
    suspend fun getPokemonList(name: String?): List<Pokemon>
    suspend fun getPokemonDetails(id: Int): PokemonDetails
    suspend fun getMoveList(name: String?): List<Move>
    suspend fun getMoveDetails(id: Int): MoveDetails
}