package com.gdlkug.poke.data.di

import com.gdlkug.poke.data.repository.PokedexRepository
import com.gdlkug.poke.data.repository.PokedexRepositoryImpl
import com.gdlkug.poke.data.repository.PokemonRepository
import com.gdlkug.poke.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @ExperimentalCoroutinesApi
    @Singleton
    @Binds
    abstract fun bindPokedexRepository(pokedexRepo: PokedexRepositoryImpl): PokedexRepository

    @Singleton
    @Binds
    abstract fun bindPokemonRepository(pokemonRepo: PokemonRepositoryImpl): PokemonRepository
}
