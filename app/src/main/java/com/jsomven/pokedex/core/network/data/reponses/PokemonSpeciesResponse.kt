package com.jsomven.pokedex.core.network.data.reponses

import com.google.gson.annotations.SerializedName
import com.jsomven.pokedex.core.domain.model.EggGroups
import com.jsomven.pokedex.core.domain.model.EvolutionChainResource
import com.jsomven.pokedex.core.domain.model.PokemonSpecies

data class PokemonSpeciesResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
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
    val url: String? = null
)

data class EvolutionChainResourceResponse(
    @SerializedName("url")
    val url: String? = null
)

fun PokemonSpeciesResponse.toPokemonSpecies(): PokemonSpecies = PokemonSpecies(
    id = id ?: -1,
    name = name ?: "",
    captureRate = captureRate ?: -1,
    baseHappiness = baseHappiness ?: -1,
    eggGroups = eggGroups.map { it.toEggGroups() },
    evolutionChain = evolutionChain?.toEvolutionChain() ?: EvolutionChainResource("")
)

fun EggGroupsResponse.toEggGroups(): EggGroups = EggGroups(
    name = name ?: ""
)

fun EvolutionChainResourceResponse.toEvolutionChain(): EvolutionChainResource = EvolutionChainResource(
    name = url ?: ""
)
