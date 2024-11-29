package com.gdlkug.poke.data.repository

import com.gdlkug.poke.data.errors.PokedexException
import com.gdlkug.poke.data.local.dao.PokemonDao
import com.gdlkug.poke.data.local.entities.toPokemonPreview
import com.gdlkug.poke.data.model.Pokedex
import com.gdlkug.poke.data.model.PokemonPreview
import com.gdlkug.poke.data.model.toPokemonEntity
import com.gdlkug.poke.data.network.dto.toPokemon
import com.gdlkug.poke.data.network.dto.toPokemonEntity
import com.gdlkug.poke.data.network.services.PokedexRetrofitService
import com.gdlkug.poke.domain.repository.PokedexRepository
import com.gdlkug.poke.util.default
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PokedexRepositoryImpl
    @Inject
    constructor(
        private val pokedexService: PokedexRetrofitService,
        private val pokemonDao: PokemonDao,
    ) : PokedexRepository {
        override fun observePokedex(): Flow<Result<Pokedex>> =
            pokemonDao.getPokemonList().flatMapLatest { localList ->
                if (localList.isEmpty()) {
                    flowOf(refreshPokedex())
                } else {
                    flowOf(Result.success(Pokedex(localList.toPokemonPreview())))
                }
            }

        override suspend fun markPokemonAsFavorite(pokemon: PokemonPreview): Long =
            pokemonDao.markPokemonAsFavorite(pokemon.copy(isFavorite = true).toPokemonEntity())

        private suspend fun refreshPokedex(): Result<Pokedex> =
            try {
                val response = pokedexService.getPokemonList()
                if (response.isSuccessful) {
                    val results = response.body()?.results.default(emptyList())
                    pokemonDao.insertPokemonList(results.toPokemonEntity())
                    Result.success(Pokedex(results.toPokemon()))
                } else {
                    Result.failure(PokedexException("Failed to fetch Pokemon list", response.code()))
                }
            } catch (e: Exception) {
                Result.failure(PokedexException(e.message ?: "Unknown error", -1))
            }
    }
