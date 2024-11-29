package com.gdlkug.poke.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gdlkug.poke.data.local.dao.PokemonDao
import com.gdlkug.poke.data.local.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
