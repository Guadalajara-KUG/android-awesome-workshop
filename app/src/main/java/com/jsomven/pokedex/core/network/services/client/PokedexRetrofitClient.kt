package com.jsomven.pokedex.core.network.services.client

import arrow.core.Either
import com.jsomven.pokedex.core.domain.errors.PokedexException
import com.jsomven.pokedex.core.network.data.reponses.PokedexResponse
import com.jsomven.pokedex.core.network.services.PokedexRetrofitService
import javax.inject.Inject

class PokedexRetrofitClient @Inject constructor(
    private val pokedexService: PokedexRetrofitService
) {
    suspend fun getListPokemon(): Either<PokedexException, PokedexResponse> = Either.catch {
        pokedexService
            .getPokemonList()
            .takeIf { it.isSuccessful }
            ?.body()
            ?: PokedexResponse()
    }.mapLeft {
        PokedexException(message = it.message, code = -1)
    }
}
