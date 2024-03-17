package com.cellstudio.tcg.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonCardResponse(
    val id: String,
    val name: String,
    val images: Map<String, String>
)