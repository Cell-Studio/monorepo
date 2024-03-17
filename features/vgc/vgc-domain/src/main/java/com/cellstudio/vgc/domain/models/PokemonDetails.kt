package com.cellstudio.vgc.domain.models

data class PokemonDetails(
    val id: Int,
    val name: String,
    val image: PokemonImage,
    val stats: Map<Stats, Int>,
    val moves: List<Move>,
    val abilities: List<Ability>,
    val type: List<PokemonType>
)