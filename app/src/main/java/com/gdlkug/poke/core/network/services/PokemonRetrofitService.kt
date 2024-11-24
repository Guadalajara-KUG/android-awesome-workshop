package com.gdlkug.poke.core.network.services

import com.gdlkug.poke.core.network.data.reponses.EvolutionChainResponse
import com.gdlkug.poke.core.network.data.reponses.PokemonAbilitiesResponse
import com.gdlkug.poke.core.network.data.reponses.PokemonSpeciesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonRetrofitService {
    @GET("pokemon-species/{idOrName}/")
    suspend fun getPokemonSpeciesDetailByIdOrName(
        @Path("idOrName") idOrName: String
    ): Response<PokemonSpeciesResponse>

    @GET("evolution-chain/{id}/")
    suspend fun getEvolutionChainById(
        @Path("id") id: String
    ): Response<EvolutionChainResponse>

    @GET("pokemon/{idOrName}/")
    suspend fun getPokemonAbilitiesByIdOrName(
        @Path("idOrName") idOrName: String
    ): Response<PokemonAbilitiesResponse>
}
