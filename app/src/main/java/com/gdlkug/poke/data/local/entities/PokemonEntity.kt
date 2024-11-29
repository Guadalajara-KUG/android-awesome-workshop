package com.gdlkug.poke.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gdlkug.poke.data.model.PokemonPreview

@Entity(
    tableName = "pokemon",
)
data class PokemonEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val isFavorite: Boolean,
)

fun PokemonEntity.toPokemonPreview(): PokemonPreview =
    PokemonPreview(
        id = id,
        name = name,
        isFavorite = isFavorite,
    )

fun List<PokemonEntity>.toPokemonPreview(): List<PokemonPreview> = map(PokemonEntity::toPokemonPreview)
