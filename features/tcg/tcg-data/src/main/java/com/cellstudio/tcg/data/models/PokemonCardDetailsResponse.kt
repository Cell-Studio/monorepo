package com.cellstudio.tcg.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class PokemonCardDetailsResponse(
    val data: PokemonCardResponse
)