package com.gdlkug.poke.data.network.dto

data class PokedexResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<PokemonPreviewResponse>? = emptyList(),
)
