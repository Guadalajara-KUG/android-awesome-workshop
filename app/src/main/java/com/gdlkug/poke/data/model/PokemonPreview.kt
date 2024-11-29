package com.gdlkug.poke.data.model

import com.gdlkug.poke.data.local.entities.PokemonEntity

data class PokemonPreview(
    val id: Int,
    val name: String,
    val isFavorite: Boolean = false
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

fun PokemonPreview.toPokemonEntity(): PokemonEntity =
    PokemonEntity(
        id = id,
        name = name,
        isFavorite = isFavorite
    )

fun List<PokemonPreview>.toPokemonEntity(): List<PokemonEntity> =
    map(PokemonPreview::toPokemonEntity)
