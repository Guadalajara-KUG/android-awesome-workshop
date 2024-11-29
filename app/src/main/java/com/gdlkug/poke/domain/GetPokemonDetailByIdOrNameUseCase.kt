package com.gdlkug.poke.domain

import com.gdlkug.poke.data.model.PokemonSpecies
import com.gdlkug.poke.data.repository.PokemonRepositoryImpl
import javax.inject.Inject

class GetPokemonDetailByIdOrNameUseCase
    @Inject
    constructor(
        private val pokemonRepositoryImpl: PokemonRepositoryImpl,
    ) {
        suspend operator fun invoke(idOrName: String): Result<PokemonSpecies> =
            pokemonRepositoryImpl.getPokemonSpeciesDetailByIdOrName(idOrName)
    }
