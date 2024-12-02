package com.gdlkug.poke.data.network.client

import com.gdlkug.poke.data.errors.PokemonException
import com.gdlkug.poke.data.network.dto.EvolutionChainResponse
import com.gdlkug.poke.data.network.dto.PokemonAbilitiesResponse
import com.gdlkug.poke.data.network.dto.PokemonSpeciesResponse
import com.gdlkug.poke.data.network.services.PokemonRetrofitService
import com.gdlkug.poke.di.IoDispatcher
import com.gdlkug.poke.util.extension.default
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonNetworkClient
    @Inject
    constructor(
        private val pokemonRetrofitService: PokemonRetrofitService,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) {
        suspend fun getPokemonSpeciesResponseByIdOrName(idOrName: String): Result<PokemonSpeciesResponse> =
            try {
                val response =
                    withContext(ioDispatcher) {
                        pokemonRetrofitService.getPokemonSpeciesByIdOrName(idOrName)
                    }
                if (response.isSuccessful) {
                    Result.success((response.body().default(PokemonSpeciesResponse())))
                } else {
                    Result.failure(PokemonException("Failed to fetch species by id or name", response.code()))
                }
            } catch (ex: Exception) {
                Result.failure(PokemonException(ex.message, -1))
            }

        suspend fun getEvolutionChainResponseById(id: String): Result<EvolutionChainResponse> =
            try {
                val response =
                    withContext(ioDispatcher) {
                        pokemonRetrofitService.getEvolutionChainById(id)
                    }
                if (response.isSuccessful) {
                    Result.success(response.body().default(EvolutionChainResponse()))
                } else {
                    Result.failure(PokemonException("Failed to fetch evolution chain by id", response.code()))
                }
            } catch (ex: Exception) {
                Result.failure(PokemonException(ex.message, -1))
            }

        suspend fun getPokemonAbilitiesResponseByIdOrName(idOrName: String): Result<PokemonAbilitiesResponse> =
            try {
                val response =
                    withContext(ioDispatcher) {
                        pokemonRetrofitService.getPokemonAbilitiesByIdOrName(idOrName)
                    }
                if (response.isSuccessful) {
                    Result.success(response.body().default(PokemonAbilitiesResponse()))
                } else {
                    Result.failure(PokemonException("Failed to fetch abilities by id or name", response.code()))
                }
            } catch (ex: Exception) {
                Result.failure(PokemonException(ex.message, -1))
            }
    }
