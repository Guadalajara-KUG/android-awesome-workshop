package com.gdlkug.poke.domain.useCase

import com.gdlkug.poke.data.model.PokemonSpecies
import com.gdlkug.poke.data.repository.PokemonRepository
import com.gdlkug.poke.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonDetailByIdOrNameUseCase
    @Inject
    constructor(
        private val pokemonRepositoryImpl: PokemonRepository,
        @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    ) {
        suspend operator fun invoke(idOrName: String): Result<PokemonSpecies> =
            withContext(defaultDispatcher) {
                pokemonRepositoryImpl.getPokemonSpeciesDetailByIdOrName(idOrName)
            }
    }
