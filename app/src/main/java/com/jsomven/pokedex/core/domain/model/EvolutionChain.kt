package com.jsomven.pokedex.core.domain.model

data class EvolutionChain(
    val id: Int,
    val chain: Chain
)

data class Chain(
    val evolvesTo: ArrayList<Species>
)

data class Species(
    val name: String,
    val url: String
) {
    private val id: Int
        get() {
            val idString = url.removePrefix("https://pokeapi.co/api/v2/pokemon-species/").removeSuffix("/")
            return idString.runCatching { toInt() }.getOrDefault(-1)
        }

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
