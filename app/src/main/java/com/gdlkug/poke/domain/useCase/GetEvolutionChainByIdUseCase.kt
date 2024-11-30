package com.gdlkug.poke.domain.useCase

import com.gdlkug.poke.data.model.EvolutionChain
import com.gdlkug.poke.data.repository.PokemonRepository
import com.gdlkug.poke.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetEvolutionChainByIdUseCase
    @Inject
    constructor(
        private val pokemonRepositoryImpl: PokemonRepository,
        @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    ) {
        suspend operator fun invoke(id: String): Result<EvolutionChain> =
            withContext(defaultDispatcher) {
                pokemonRepositoryImpl.getEvolutionChainById(id)
            }
    }
