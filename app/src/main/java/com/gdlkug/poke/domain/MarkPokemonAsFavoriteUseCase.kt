package com.gdlkug.poke.domain

import com.gdlkug.poke.data.model.PokemonPreview
import com.gdlkug.poke.data.repository.PokedexRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MarkPokemonAsFavoriteUseCase
    @Inject
    constructor(
        private val pokedexRepositoryImpl: PokedexRepositoryImpl,
    ) {
        suspend operator fun invoke(pokemon: PokemonPreview): Long = pokedexRepositoryImpl.markPokemonAsFavorite(pokemon)
    }
