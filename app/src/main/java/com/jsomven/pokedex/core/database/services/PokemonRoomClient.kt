package com.jsomven.pokedex.core.database.services

import com.jsomven.pokedex.core.database.data.dao.PokemonDao
import com.jsomven.pokedex.core.database.data.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRoomClient @Inject constructor(
    private val pokemonDao: PokemonDao
) {
    suspend fun getListPokemon(): Flow<List<PokemonEntity>> = pokemonDao.getPokemonList()

    suspend fun insertPokemonListToRoom(pokemonList: List<PokemonEntity>) = pokemonDao.insertPokemonList(pokemonList)

    suspend fun markPokemonAsFavorite(pokemon: PokemonEntity) = pokemonDao.markPokemonAsFavorite(pokemon)
}
