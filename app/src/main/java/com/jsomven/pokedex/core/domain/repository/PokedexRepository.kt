package com.jsomven.pokedex.core.domain.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.jsomven.pokedex.core.database.data.entities.toPokemonPreview
import com.jsomven.pokedex.core.database.services.PokemonRoomClient
import com.jsomven.pokedex.core.domain.errors.PokedexException
import com.jsomven.pokedex.core.domain.model.Pokedex
import com.jsomven.pokedex.core.domain.model.PokemonPreview
import com.jsomven.pokedex.core.domain.model.toPokemonEntity
import com.jsomven.pokedex.core.network.data.reponses.toPokedex
import com.jsomven.pokedex.core.network.services.client.PokedexRetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokedexRepository @Inject constructor(
    private val pokedexRetrofitClient: PokedexRetrofitClient,
    private val pokemonRoomClient: PokemonRoomClient
) {
    suspend fun getPokedexList(): Either<PokedexException, Pokedex> =
        pokedexRetrofitClient.getListPokemon().map { it.toPokedex() }

    suspend fun getPokedexRoomList(): Either<PokedexException, Flow<List<PokemonPreview>>> =
        pokemonRoomClient.runCatching {
            getListPokemon().map { entity ->
                entity.map { it.toPokemonPreview() }
            }.right()
        }.getOrElse { PokedexException(message = it.message, code = -1).left() }

    suspend fun insertPokemonListToRoom(pokemonList: List<PokemonPreview>): Either<PokedexException, List<Long>> =
        pokemonRoomClient.runCatching {
            insertPokemonListToRoom(pokemonList.map { it.toPokemonEntity() }).right()
        }.getOrElse { PokedexException(message = it.message, code = -1).left() }

    suspend fun markPokemonAsFavorite(pokemon: PokemonPreview): Either<PokedexException, Long> =
        pokemonRoomClient.runCatching {
            markPokemonAsFavorite(pokemon.copy(isFavorite = true).toPokemonEntity()).right()
        }.getOrElse { PokedexException(message = it.message, code = -1).left() }
}
