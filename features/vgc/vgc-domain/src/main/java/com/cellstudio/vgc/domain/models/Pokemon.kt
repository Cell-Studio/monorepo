package com.cellstudio.vgc.domain.models

data class Pokemon(
    val id: Int,
    val name: String,
    val image: PokemonImage
)

data class PokemonImage(
    val official: String,
    val sprite: String,
    val icon: String
)