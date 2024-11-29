package com.gdlkug.poke.domain

import com.gdlkug.poke.data.model.PokemonAbilities
import com.gdlkug.poke.data.repository.PokemonRepositoryImpl
import javax.inject.Inject

class GetPokemonAbilitiesByIdOrNameUseCase
    @Inject
    constructor(
        private val pokemonRepositoryImpl: PokemonRepositoryImpl,
    ) {
        suspend operator fun invoke(idOrName: String): Result<PokemonAbilities> =
            pokemonRepositoryImpl.getPokemonAbilitiesByIdOrName(idOrName)
    }
