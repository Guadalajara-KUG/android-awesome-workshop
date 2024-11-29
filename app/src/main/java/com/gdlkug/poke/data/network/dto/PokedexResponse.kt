package com.gdlkug.poke.data.network.dto

import com.gdlkug.poke.data.local.entities.PokemonEntity
import com.gdlkug.poke.data.model.Pokedex
import com.google.gson.annotations.SerializedName

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
