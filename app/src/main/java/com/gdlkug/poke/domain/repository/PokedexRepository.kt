package com.gdlkug.poke.domain.repository

import com.gdlkug.poke.data.model.Pokedex
import com.gdlkug.poke.data.model.PokemonPreview
import kotlinx.coroutines.flow.Flow

interface PokedexRepository {
    fun observePokedex(): Flow<Result<Pokedex>>

    suspend fun markPokemonAsFavorite(pokemon: PokemonPreview): Long
}
