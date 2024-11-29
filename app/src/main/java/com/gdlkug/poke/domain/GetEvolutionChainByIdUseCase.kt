package com.gdlkug.poke.domain

import com.gdlkug.poke.data.model.EvolutionChain
import com.gdlkug.poke.data.repository.PokemonRepositoryImpl
import javax.inject.Inject

class GetEvolutionChainByIdUseCase
    @Inject
    constructor(
        private val pokemonRepositoryImpl: PokemonRepositoryImpl,
    ) {
        suspend operator fun invoke(id: String): Result<EvolutionChain> = pokemonRepositoryImpl.getEvolutionChainById(id)
    }
