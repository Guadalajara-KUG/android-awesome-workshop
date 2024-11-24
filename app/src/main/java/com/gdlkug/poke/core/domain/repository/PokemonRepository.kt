package com.gdlkug.poke.core.domain.repository

import arrow.core.Either
import com.gdlkug.poke.core.domain.errors.PokemonException
import com.gdlkug.poke.core.domain.model.EvolutionChain
import com.gdlkug.poke.core.domain.model.PokemonAbilities
import com.gdlkug.poke.core.domain.model.PokemonSpecies
import com.gdlkug.poke.core.network.data.reponses.toEvolutionChain
import com.gdlkug.poke.core.network.data.reponses.toPokemonAbilities
import com.gdlkug.poke.core.network.data.reponses.toPokemonSpecies
import com.gdlkug.poke.core.network.services.client.PokemonRetrofitClient
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonClient: PokemonRetrofitClient
) {
    suspend fun getPokemonSpeciesDetailByIdOrName(idOrName: String): Either<PokemonException, PokemonSpecies> =
        pokemonClient.getPokemonSpeciesDetailByIdOrName(idOrName).map { it.toPokemonSpecies() }

    suspend fun getEvolutionChainById(id: String): Either<PokemonException, EvolutionChain> =
        pokemonClient.getEvolutionChainById(id).map { it.toEvolutionChain() }

    suspend fun getPokemonAbilitiesByIdOrName(idOrName: String): Either<PokemonException, PokemonAbilities> =
        pokemonClient.getPokemonAbilitiesByIdOrName(idOrName).map { it.toPokemonAbilities() }
}
