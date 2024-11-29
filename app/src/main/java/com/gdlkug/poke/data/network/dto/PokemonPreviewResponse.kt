package com.gdlkug.poke.data.network.dto

import com.gdlkug.poke.data.local.entities.PokemonEntity
import com.gdlkug.poke.data.model.PokemonPreview
import com.gdlkug.poke.util.default
import com.google.gson.annotations.SerializedName

data class PokemonPreviewResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun PokemonPreviewResponse.toPokemon(): PokemonPreview {
    val idString = url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
    val idInt = idString.toIntOrNull().default(-1)
    return PokemonPreview(
        id = idInt,
        name = name
    )
}

fun List<PokemonPreviewResponse>.toPokemon() =
    map(PokemonPreviewResponse::toPokemon)

fun PokemonPreviewResponse.toPokemonEntity(): PokemonEntity {
    val idString = url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
    val idInt = idString.toIntOrNull().default(-1)
    return PokemonEntity (
        id = idInt,
        name = name,
        isFavorite = false
    )
}

fun List<PokemonPreviewResponse>.toPokemonEntity() =
    map(PokemonPreviewResponse::toPokemonEntity)

