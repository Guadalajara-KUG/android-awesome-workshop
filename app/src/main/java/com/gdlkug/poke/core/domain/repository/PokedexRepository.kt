package com.gdlkug.poke.core.domain.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.gdlkug.poke.core.database.data.entities.toPokemonPreview
import com.gdlkug.poke.core.domain.model.toPokemonEntity
import com.gdlkug.poke.core.network.data.reponses.toPokedex
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val pokedexRetrofitClient: com.gdlkug.poke.core.network.services.client.PokedexRetrofitClient,
    private val pokemonRoomClient: com.gdlkug.poke.core.database.services.PokemonRoomClient
) {
    suspend fun getPokedexList(): Either<com.gdlkug.poke.core.domain.errors.PokedexException, com.gdlkug.poke.core.domain.model.Pokedex> =
        pokedexRetrofitClient.getListPokemon().map { it.toPokedex() }

    suspend fun getPokedexRoomList(): Either<com.gdlkug.poke.core.domain.errors.PokedexException, Flow<List<com.gdlkug.poke.core.domain.model.PokemonPreview>>> =
        pokemonRoomClient.runCatching {
            getListPokemon().map { entity ->
                entity.map { it.toPokemonPreview() }
            }.right()
        }.getOrElse { com.gdlkug.poke.core.domain.errors.PokedexException(
            message = it.message,
            code = -1
        ).left() }

    suspend fun insertPokemonListToRoom(pokemonList: List<com.gdlkug.poke.core.domain.model.PokemonPreview>): Either<com.gdlkug.poke.core.domain.errors.PokedexException, List<Long>> =
        pokemonRoomClient.runCatching {
            insertPokemonListToRoom(pokemonList.map { it.toPokemonEntity() }).right()
        }.getOrElse { com.gdlkug.poke.core.domain.errors.PokedexException(
            message = it.message,
            code = -1
        ).left() }

    suspend fun markPokemonAsFavorite(pokemon: com.gdlkug.poke.core.domain.model.PokemonPreview): Either<com.gdlkug.poke.core.domain.errors.PokedexException, Long> =
        pokemonRoomClient.runCatching {
            markPokemonAsFavorite(pokemon.copy(isFavorite = true).toPokemonEntity()).right()
        }.getOrElse { com.gdlkug.poke.core.domain.errors.PokedexException(
            message = it.message,
            code = -1
        ).left() }
}
