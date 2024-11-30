package com.gdlkug.poke.data.network.dto

import com.gdlkug.poke.data.model.Chain
import com.gdlkug.poke.data.model.EvolutionChain
import com.gdlkug.poke.data.model.Species
import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(
    val chain: ChainResponse? = null,
    val id: Int? = null,
)

data class ChainResponse(
    val species: SpeciesResponse? = null,
    @SerializedName("evolves_to")
    val evolvesTo: ArrayList<EvolvesToResponse>? = null,
)

data class EvolvesToResponse(
    val species: SpeciesResponse? = null,
    @SerializedName("evolves_to")
    val evolvesTo: ArrayList<EvolvesToResponse>? = null,
)

data class SpeciesResponse(
    val name: String? = null,
    val url: String? = null,
)

fun EvolutionChainResponse.toEvolutionChain(): EvolutionChain {
    val evolutionChainOrder = arrayListOf<Species>()
    chain?.species?.toSpecies()?.let { evolutionChainOrder.add(it) }
    var chainArray = chain?.evolvesTo

    while (chainArray?.size != 0 && chainArray?.get(0) != null) {
        chainArray[0].species?.toSpecies()?.let { species -> evolutionChainOrder.add(species) }
        chainArray = chainArray[0].evolvesTo
    }

    return EvolutionChain(
        id = id ?: -1,
        chain = Chain(evolutionChainOrder),
    )
}

fun SpeciesResponse.toSpecies() =
    Species(
        name = name ?: "",
        url = url ?: "",
    )
