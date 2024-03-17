package com.cellstudio.tcg.data.apis

import com.cellstudio.tcg.data.models.PokemonCardDetailsResponse
import com.cellstudio.tcg.data.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface TcgApi {
    @GET("/v2/cards")
    suspend fun search(
        @Query("name") query: String
    ): SearchResponse

    @GET("/v2/cards/{id}")
    suspend fun getCardDetails(
        @Path("id") id: String,
    ): PokemonCardDetailsResponse
}