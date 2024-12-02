package com.gdlkug.poke.data.repository

import com.gdlkug.poke.data.errors.PokedexException
import com.gdlkug.poke.data.local.client.PokedexLocalClient
import com.gdlkug.poke.data.local.entities.toPokemonPreview
import com.gdlkug.poke.data.model.Pokedex
import com.gdlkug.poke.data.model.PokemonPreview
import com.gdlkug.poke.data.model.toPokemonEntity
import com.gdlkug.poke.data.network.client.PokedexNetworkClient
import com.gdlkug.poke.data.network.dto.toPokemon
import com.gdlkug.poke.data.network.dto.toPokemonEntity
import com.gdlkug.poke.util.extension.default
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PokedexRepositoryImpl
    @Inject
    constructor(
        private val pokedexNetworkClient: PokedexNetworkClient,
        private val pokedexLocalClient: PokedexLocalClient,
    ) : PokedexRepository {
        override fun observePokedex(): Flow<Result<Pokedex>> =
            pokedexLocalClient.getPokemonList().flatMapLatest { localList ->
                if (localList.isEmpty()) {
                    flowOf(refreshPokedex())
                } else {
                    flowOf(Result.success(Pokedex(localList.toPokemonPreview())))
                }
            }

        override suspend fun markPokemonAsFavorite(pokemon: PokemonPreview): Long =
            pokedexLocalClient.markPokemonAsFavorite(pokemon.copy(isFavorite = true).toPokemonEntity())

        private suspend fun refreshPokedex(): Result<Pokedex> {
            val response = pokedexNetworkClient.getListPokemon()
            return response.fold(
                onSuccess = {
                    val list = it.results.default(emptyList())
                    pokedexLocalClient.insertPokemonList(list.toPokemonEntity())
                    Result.success(Pokedex(list.toPokemon()))
                },
                onFailure = {
                    val pokemonException = it as? PokedexException
                    Result.failure(pokemonException.default(PokedexException("Unknown error", -1)))
                },
            )
        }
    }
