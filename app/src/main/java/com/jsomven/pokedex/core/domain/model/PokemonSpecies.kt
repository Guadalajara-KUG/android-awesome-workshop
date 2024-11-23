package com.jsomven.pokedex.core.domain.model

data class PokemonSpecies(
    val id: Int,
    val name: String,
    val captureRate: Int,
    val baseHappiness: Int,
    val eggGroups: List<EggGroups>,
    val evolutionChain: EvolutionChainResource
) {
    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}

data class EggGroups(
    val name: String
) {
    override fun toString(): String = name
}

data class EvolutionChainResource(
    val name: String
) {
    val id: String
        get() = name.removePrefix("https://pokeapi.co/api/v2/evolution-chain/").removeSuffix("/")
}
