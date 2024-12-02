package com.gdlkug.poke.data.network.client

import com.gdlkug.poke.errors.PokedexException
import com.gdlkug.poke.data.network.dto.PokedexResponse
import com.gdlkug.poke.data.network.services.PokedexRetrofitService
import com.gdlkug.poke.di.IoDispatcher
import com.gdlkug.poke.util.extension.default
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokedexNetworkClient
    @Inject
    constructor(
        private val pokedexService: PokedexRetrofitService,
        @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    ) {
        suspend fun getListPokemon(): Result<PokedexResponse> =
            try {
                val response =
                    withContext(ioDispatcher) {
                        pokedexService.getPokemonList()
                    }
                if (response.isSuccessful) {
                    val results = response.body().default(PokedexResponse())
                    Result.success(results)
                } else {
                    Result.failure(PokedexException("Failed to fetch Pokemon list", response.code()))
                }
            } catch (e: Exception) {
                Result.failure(PokedexException(e.message ?: "Unknown error", -1))
            }
    }
