package com.gdlkug.poke.data.network.dto

import com.gdlkug.poke.data.model.Chain
import com.gdlkug.poke.data.model.EvolutionChain
import com.gdlkug.poke.data.model.Species
import com.google.gson.annotations.SerializedName

data class EvolutionChainResponse(
    @SerializedName("chain")
    val chain: ChainResponse? = null,
    @SerializedName("id")
    val id: Int? = null
)

data class ChainResponse(
    @SerializedName("species")
    val species: SpeciesResponse? = null,
    @SerializedName("evolves_to")
    val evolvesTo: ArrayList<EvolvesToResponse>? = null
)

data class EvolvesToResponse(
    @SerializedName("species")
    val species: SpeciesResponse? = null,
    @SerializedName("evolves_to")
    val evolvesTo: ArrayList<EvolvesToResponse>? = null
)

data class SpeciesResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
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
        chain = Chain(evolutionChainOrder)
    )
}

fun SpeciesResponse.toSpecies() = Species(
    name = name ?: "",
    url = url ?: ""
)