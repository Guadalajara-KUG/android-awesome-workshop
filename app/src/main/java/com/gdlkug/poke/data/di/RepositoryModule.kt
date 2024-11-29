package com.gdlkug.poke.data.di

import com.gdlkug.poke.data.repository.PokedexRepositoryImpl
import com.gdlkug.poke.data.repository.PokemonRepositoryImpl
import com.gdlkug.poke.domain.repository.PokedexRepository
import com.gdlkug.poke.domain.repository.PokemonRepository
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