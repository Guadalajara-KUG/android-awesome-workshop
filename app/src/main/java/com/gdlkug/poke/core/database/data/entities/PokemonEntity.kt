package com.gdlkug.poke.core.database.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gdlkug.poke.core.domain.model.PokemonPreview

@Entity(
    tableName = "pokemon"
)
data class PokemonEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val isFavorite: Boolean
)

fun PokemonEntity.toPokemonPreview(): PokemonPreview = PokemonPreview(
    id = id,
    name = name,
    isFavorite = isFavorite
)
