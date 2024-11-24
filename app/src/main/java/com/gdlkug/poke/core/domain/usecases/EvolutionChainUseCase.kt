package com.gdlkug.poke.core.domain.usecases

import arrow.core.Either
import com.gdlkug.poke.core.domain.errors.PokemonException
import com.gdlkug.poke.core.domain.model.EvolutionChain
import com.gdlkug.poke.core.domain.repository.PokemonRepository
import javax.inject.Inject

class EvolutionChainUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) {
    suspend operator fun invoke(id: String): Either<PokemonException, EvolutionChain> =
        pokemonRepository.getEvolutionChainById(id)
}
