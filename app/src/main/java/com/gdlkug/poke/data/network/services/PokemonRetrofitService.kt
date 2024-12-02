package com.gdlkug.poke.data.network.services

import com.gdlkug.poke.data.network.dto.EvolutionChainResponse
import com.gdlkug.poke.data.network.dto.PokemonAbilitiesResponse
import com.gdlkug.poke.data.network.dto.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonRetrofitService {
    @GET("pokemon-species/{idOrName}/")
    suspend fun getPokemonSpeciesByIdOrName(
        @Path("idOrName") idOrName: String,
    ): Response<PokemonSpeciesResponse>

    @GET("evolution-chain/{id}/")
    suspend fun getEvolutionChainById(
        @Path("id") id: String,
    ): Response<EvolutionChainResponse>

    @GET("pokemon/{idOrName}/")
    suspend fun getPokemonAbilitiesByIdOrName(
        @Path("idOrName") idOrName: String,
    ): Response<PokemonAbilitiesResponse>
}
