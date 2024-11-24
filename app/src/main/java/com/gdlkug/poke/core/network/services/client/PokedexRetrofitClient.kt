package com.gdlkug.poke.core.network.services.client

import arrow.core.Either
import com.gdlkug.poke.core.domain.errors.PokedexException
import com.gdlkug.poke.core.network.data.reponses.PokedexResponse
import com.gdlkug.poke.core.network.services.PokedexRetrofitService
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
        PokedexException(
            message = it.message,
            code = -1
        )
    }
}
