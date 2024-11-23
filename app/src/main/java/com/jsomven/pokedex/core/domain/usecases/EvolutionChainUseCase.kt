package com.jsomven.pokedex.core.domain.usecases

import arrow.core.Either
import com.jsomven.pokedex.core.domain.errors.PokemonException
import com.jsomven.pokedex.core.domain.model.EvolutionChain
import com.jsomven.pokedex.core.domain.repository.PokemonRepository
import javax.inject.Inject

class EvolutionChainUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(id: String): Either<PokemonException, EvolutionChain> =
        pokemonRepository.getEvolutionChainById(id)
}
