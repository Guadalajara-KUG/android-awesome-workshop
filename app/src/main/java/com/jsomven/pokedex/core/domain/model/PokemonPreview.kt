package com.jsomven.pokedex.core.domain.model

import com.jsomven.pokedex.core.database.data.entities.PokemonEntity

data class PokemonPreview(
    val id: Int,
    val name: String,
    val isFavorite: Boolean = false
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

fun PokemonPreview.toPokemonEntity(): PokemonEntity = PokemonEntity(
    id = id,
    name = name,
    isFavorite = isFavorite
)
