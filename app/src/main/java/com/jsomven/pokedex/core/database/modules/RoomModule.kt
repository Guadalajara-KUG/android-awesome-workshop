package com.jsomven.pokedex.core.database.modules

import android.content.Context
import androidx.room.Room
import com.jsomven.pokedex.core.database.AppDatabase
import com.jsomven.pokedex.core.database.data.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {
    private const val DATABASE_NAME = "pokedex"

    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: AppDatabase): PokemonDao =
        appDatabase.pokemonDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
}
