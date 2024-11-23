package com.jsomven.pokedex.core.domain.repository

import arrow.core.Either
import com.jsomven.pokedex.core.domain.errors.PokemonException
import com.jsomven.pokedex.core.domain.model.EvolutionChain
import com.jsomven.pokedex.core.domain.model.PokemonAbilities
import com.jsomven.pokedex.core.domain.model.PokemonSpecies
import com.jsomven.pokedex.core.network.data.reponses.toEvolutionChain
import com.jsomven.pokedex.core.network.data.reponses.toPokemonAbilities
import com.jsomven.pokedex.core.network.data.reponses.toPokemonSpecies
import com.jsomven.pokedex.core.network.services.client.PokemonRetrofitClient
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
