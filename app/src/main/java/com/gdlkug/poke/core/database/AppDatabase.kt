package com.gdlkug.poke.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gdlkug.poke.core.database.data.dao.PokemonDao
import com.gdlkug.poke.core.database.data.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
