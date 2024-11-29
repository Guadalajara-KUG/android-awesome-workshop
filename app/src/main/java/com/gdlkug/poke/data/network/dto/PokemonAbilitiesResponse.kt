package com.gdlkug.poke.data.network.dto

import com.gdlkug.poke.data.model.PokemonAbilities
import com.google.gson.annotations.SerializedName

data class PokemonAbilitiesResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("abilities")
    val abilities: ArrayList<AbilitiesResponse> = arrayListOf(),
)

data class AbilitiesResponse(
    @SerializedName("ability")
    val ability: AbilityResponse? = AbilityResponse()
)

data class AbilityResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)

fun PokemonAbilitiesResponse.toPokemonAbilities(): PokemonAbilities {
    val abilities = abilities.filter { it.ability != null }.map { response ->
        com.gdlkug.poke.data.model.Ability(
            name = response.ability?.name ?: "",
            url = response.ability?.url ?: ""
        )
    }

    return PokemonAbilities(
        id = id ?: -1,
        name = name ?: "",
        abilities = abilities
    )
}
