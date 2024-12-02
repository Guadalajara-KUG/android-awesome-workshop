package com.gdlkug.poke.data.local.client

import com.gdlkug.poke.data.local.dao.PokemonDao
import com.gdlkug.poke.data.local.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokedexLocalClient
    @Inject
    constructor(
        private val pokemonDao: PokemonDao,
    ) {
        fun getPokemonList(): Flow<List<PokemonEntity>> = pokemonDao.getPokemonList()

        suspend fun insertPokemonList(pokemonList: List<PokemonEntity>) = pokemonDao.insertPokemonList(pokemonList)

        suspend fun markPokemonAsFavorite(pokemon: PokemonEntity) = pokemonDao.markPokemonAsFavorite(pokemon)
    }
