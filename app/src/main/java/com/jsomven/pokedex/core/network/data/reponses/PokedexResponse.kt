package com.jsomven.pokedex.core.network.data.reponses

import com.google.gson.annotations.SerializedName
import com.jsomven.pokedex.core.domain.model.Pokedex

data class PokedexResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val results: List<PokemonPreviewResponse>? = emptyList()
)

fun PokedexResponse.toPokedex() = Pokedex(
    pokemonList = results?.map { it.toPokemon() } ?: emptyList()
)
