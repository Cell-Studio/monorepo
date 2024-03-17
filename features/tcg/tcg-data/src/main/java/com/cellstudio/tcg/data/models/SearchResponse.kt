package com.cellstudio.tcg.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class SearchResponse(
    val data: List<PokemonCardResponse>,
    val page: Int,
    val pageSize: Int,
    val count: Int,
    val totalCount: Int
)

