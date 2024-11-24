package com.gdlkug.poke.core.network.services

import com.gdlkug.poke.core.network.data.reponses.PokedexResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexRetrofitService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 150
    ): Response<PokedexResponse>
}
