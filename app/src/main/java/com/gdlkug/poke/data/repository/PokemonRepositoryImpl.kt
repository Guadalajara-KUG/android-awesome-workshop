package com.gdlkug.poke.data.repository

import com.gdlkug.poke.data.model.EvolutionChain
import com.gdlkug.poke.data.model.PokemonAbilities
import com.gdlkug.poke.data.model.PokemonSpecies
import com.gdlkug.poke.data.network.client.PokemonNetworkClient
import com.gdlkug.poke.data.network.dto.toEvolutionChain
import com.gdlkug.poke.data.network.dto.toPokemonAbilities
import com.gdlkug.poke.data.network.dto.toPokemonSpecies
import javax.inject.Inject

class PokemonRepositoryImpl
    @Inject
    constructor(
        private val pokemonNetworkClient: PokemonNetworkClient,
    ) : PokemonRepository {
        override suspend fun getPokemonSpeciesDetailByIdOrName(idOrName: String): Result<PokemonSpecies> =
            pokemonNetworkClient.getPokemonSpeciesResponseByIdOrName(idOrName).map {
                it.toPokemonSpecies()
            }

        override suspend fun getEvolutionChainById(id: String): Result<EvolutionChain> =
            pokemonNetworkClient.getEvolutionChainResponseById(id).map {
                it.toEvolutionChain()
            }

        override suspend fun getPokemonAbilitiesByIdOrName(idOrName: String): Result<PokemonAbilities> =
            pokemonNetworkClient.getPokemonAbilitiesResponseByIdOrName(idOrName).map {
                it.toPokemonAbilities()
            }
    }
