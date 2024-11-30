package com.gdlkug.poke.data.network.dto

import com.gdlkug.poke.data.model.EggGroups
import com.gdlkug.poke.data.model.EvolutionChainResource
import com.gdlkug.poke.data.model.PokemonSpecies
import com.google.gson.annotations.SerializedName

data class PokemonSpeciesResponse(
    val id: Int? = null,
    val name: String? = null,
    @SerializedName("capture_rate")
    val captureRate: Int? = null,
    @SerializedName("base_happiness")
    val baseHappiness: Int? = null,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupsResponse> = arrayListOf(),
    @SerializedName("evolution_chain")
    val evolutionChain: EvolutionChainResourceResponse? = EvolutionChainResourceResponse(),
)

data class EggGroupsResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null,
)

data class EvolutionChainResourceResponse(
    @SerializedName("url")
    val url: String? = null,
)

fun PokemonSpeciesResponse.toPokemonSpecies(): PokemonSpecies =
    PokemonSpecies(
        id = id ?: -1,
        name = name ?: "",
        captureRate = captureRate ?: -1,
        baseHappiness = baseHappiness ?: -1,
        eggGroups = eggGroups.map { it.toEggGroups() },
        evolutionChain =
            evolutionChain?.toEvolutionChain()
                ?: EvolutionChainResource(""),
    )

fun EggGroupsResponse.toEggGroups(): EggGroups =
    EggGroups(
        name = name ?: "",
    )

fun EvolutionChainResourceResponse.toEvolutionChain(): EvolutionChainResource =
    EvolutionChainResource(
        name = url ?: "",
    )
