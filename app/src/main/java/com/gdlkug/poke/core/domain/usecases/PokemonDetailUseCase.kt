package com.gdlkug.poke.core.domain.usecases

import arrow.core.Either
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: com.gdlkug.poke.core.domain.repository.PokemonRepository
) {
    suspend operator fun invoke(idOrName: String): Either<com.gdlkug.poke.core.domain.errors.PokemonException, com.gdlkug.poke.core.domain.model.PokemonSpecies> =
        pokemonRepository.getPokemonSpeciesDetailByIdOrName(idOrName)
}
