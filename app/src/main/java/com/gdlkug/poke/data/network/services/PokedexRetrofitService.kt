package com.gdlkug.poke.data.network.services

import com.gdlkug.poke.data.network.dto.PokedexResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexRetrofitService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 150
    ): Response<PokedexResponse>
}
