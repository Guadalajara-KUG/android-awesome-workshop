package com.gdlkug.poke.data.repository

import com.gdlkug.poke.data.errors.PokemonException
import com.gdlkug.poke.data.model.EvolutionChain
import com.gdlkug.poke.data.model.PokemonAbilities
import com.gdlkug.poke.data.model.PokemonSpecies
import com.gdlkug.poke.data.network.dto.EvolutionChainResponse
import com.gdlkug.poke.data.network.dto.PokemonAbilitiesResponse
import com.gdlkug.poke.data.network.dto.PokemonSpeciesResponse
import com.gdlkug.poke.data.network.dto.toEvolutionChain
import com.gdlkug.poke.data.network.dto.toPokemonAbilities
import com.gdlkug.poke.data.network.dto.toPokemonSpecies
import com.gdlkug.poke.data.network.services.PokemonRetrofitService
import com.gdlkug.poke.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonService: PokemonRetrofitService
): PokemonRepository {
    override suspend fun getPokemonSpeciesDetailByIdOrName(idOrName: String): Result<PokemonSpecies> {
        return try {
            val response = pokemonService.getPokemonSpeciesDetailByIdOrName(idOrName)
            if (response.isSuccessful) {
                Result.success((response.body() ?: PokemonSpeciesResponse()).toPokemonSpecies())
            } else {
                Result.failure(PokemonException("Failed to fetch species by id or name", response.code()))
            }
        } catch (ex: Exception) {
            Result.failure(PokemonException(ex.message, -1))
        }
    }

    override suspend fun getEvolutionChainById(id: String): Result<EvolutionChain> {
        return try {
            val response = pokemonService.getEvolutionChainById(id)
            if (response.isSuccessful) {
                Result.success((response.body() ?: EvolutionChainResponse()).toEvolutionChain())
            } else {
                Result.failure(PokemonException("Failed to fetch evolution chain by id", response.code()))
            }
        } catch (ex: Exception) {
            Result.failure(PokemonException(ex.message, -1))
        }
    }

    override suspend fun getPokemonAbilitiesByIdOrName(idOrName: String): Result<PokemonAbilities> {
        return try {
            val response = pokemonService.getPokemonAbilitiesByIdOrName(idOrName)
            if (response.isSuccessful) {
                Result.success((response.body() ?: PokemonAbilitiesResponse()).toPokemonAbilities())
            } else {
                Result.failure(PokemonException("Failed to fetch abilities by id or name", response.code()))
            }
        } catch (ex: Exception) {
            Result.failure(PokemonException(ex.message, -1))
        }
    }
}
