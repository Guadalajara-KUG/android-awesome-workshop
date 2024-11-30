package com.gdlkug.poke.data.repository

import com.gdlkug.poke.data.model.EvolutionChain
import com.gdlkug.poke.data.model.PokemonAbilities
import com.gdlkug.poke.data.model.PokemonSpecies

interface PokemonRepository {
    suspend fun getPokemonSpeciesDetailByIdOrName(idOrName: String): Result<PokemonSpecies>

    suspend fun getEvolutionChainById(id: String): Result<EvolutionChain>

    suspend fun getPokemonAbilitiesByIdOrName(idOrName: String): Result<PokemonAbilities>
}
