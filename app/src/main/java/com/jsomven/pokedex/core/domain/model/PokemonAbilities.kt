package com.jsomven.pokedex.core.domain.model

data class PokemonAbilities(
    val id: Int,
    val name: String,
    val abilities: List<Ability>
)

data class Ability(
    val name: String,
    val url: String
)
