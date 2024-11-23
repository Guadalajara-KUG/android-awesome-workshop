package com.jsomven.pokedex.core.network.data.reponses

import com.google.gson.annotations.SerializedName
import com.jsomven.pokedex.core.domain.model.PokemonPreview

data class PokemonPreviewResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun PokemonPreviewResponse.toPokemon(): PokemonPreview {
    val idString = url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/")
    val idInt = idString.runCatching { toInt() }.getOrDefault(-1)
    return PokemonPreview(
        id = idInt,
        name = name
    )
}
