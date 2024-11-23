package com.jsomven.pokedex.core.domain.usecases

import arrow.core.Either
import com.jsomven.pokedex.core.domain.errors.PokemonException
import com.jsomven.pokedex.core.domain.model.PokemonAbilities
import com.jsomven.pokedex.core.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonAbilitiesUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(idOrName: String): Either<PokemonException, PokemonAbilities> =
        pokemonRepository.getPokemonAbilitiesByIdOrName(idOrName)
}
