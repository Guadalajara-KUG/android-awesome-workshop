package com.gdlkug.poke.data.local.di

import com.gdlkug.poke.data.local.database.AppDatabase
import com.gdlkug.poke.data.local.dao.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideChannelDao(appDatabase: AppDatabase): PokemonDao =
        appDatabase.pokemonDao()
}