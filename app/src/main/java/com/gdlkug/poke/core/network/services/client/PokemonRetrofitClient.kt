package com.gdlkug.poke.core.network.services.client

import arrow.core.Either
import com.gdlkug.poke.core.domain.errors.PokemonException
import com.gdlkug.poke.core.network.data.reponses.EvolutionChainResponse
import com.gdlkug.poke.core.network.data.reponses.PokemonAbilitiesResponse
import com.gdlkug.poke.core.network.data.reponses.PokemonSpeciesResponse
import com.gdlkug.poke.core.network.services.PokemonRetrofitService
import javax.inject.Inject

class PokemonRetrofitClient @Inject constructor(
    private val pokemonService: PokemonRetrofitService
) {
    suspend fun getPokemonSpeciesDetailByIdOrName(idOrName: String): Either<PokemonException, PokemonSpeciesResponse> =
        Either.catch {
            pokemonService
                .getPokemonSpeciesDetailByIdOrName(idOrName)
                .takeIf { it.isSuccessful }
                ?.body()
                ?: PokemonSpeciesResponse()
        }.mapLeft {
            PokemonException(message = it.message ?: "Error not found", code = -1)
        }

    suspend fun getEvolutionChainById(id: String): Either<PokemonException, EvolutionChainResponse> =
        Either.catch {
            pokemonService
                .getEvolutionChainById(id)
                .takeIf { it.isSuccessful }
                ?.body()
                ?: EvolutionChainResponse()
        }.mapLeft {
            PokemonException(message = it.message ?: "Error", code = -1)
        }

    suspend fun getPokemonAbilitiesByIdOrName(idOrName: String): Either<PokemonException, PokemonAbilitiesResponse> =
        Either.catch {
            pokemonService
                .getPokemonAbilitiesByIdOrName(idOrName)
                .takeIf { it.isSuccessful }
                ?.body()
                ?: PokemonAbilitiesResponse()
        }.mapLeft {
            PokemonException(message = it.message ?: "Error", code = -1)
        }
}
