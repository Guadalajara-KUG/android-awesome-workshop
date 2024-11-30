package com.gdlkug.poke.domain.useCase

import com.gdlkug.poke.data.model.PokemonPreview
import com.gdlkug.poke.data.repository.PokedexRepository
import com.gdlkug.poke.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MarkPokemonAsFavoriteUseCase
    @Inject
    constructor(
        private val pokedexRepositoryImpl: PokedexRepository,
        @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    ) {
        suspend operator fun invoke(pokemon: PokemonPreview): Long =
            withContext(defaultDispatcher) {
                pokedexRepositoryImpl.markPokemonAsFavorite(pokemon)
            }
    }
