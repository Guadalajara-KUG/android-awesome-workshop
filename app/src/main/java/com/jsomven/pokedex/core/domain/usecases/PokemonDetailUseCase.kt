package com.jsomven.pokedex.core.domain.usecases

import arrow.core.Either
import com.jsomven.pokedex.core.domain.repository.PokemonRepository
import com.jsomven.pokedex.core.domain.errors.PokemonException
import com.jsomven.pokedex.core.domain.model.PokemonSpecies
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(idOrName: String): Either<PokemonException, PokemonSpecies> =
        pokemonRepository.getPokemonSpeciesDetailByIdOrName(idOrName)
}
