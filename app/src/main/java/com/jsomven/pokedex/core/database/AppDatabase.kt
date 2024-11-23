package com.jsomven.pokedex.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jsomven.pokedex.core.database.data.dao.PokemonDao
import com.jsomven.pokedex.core.database.data.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
