package com.gdlkug.poke.core.domain.usecases

import arrow.core.Either
import com.gdlkug.poke.core.domain.errors.PokemonException
import com.gdlkug.poke.core.domain.model.PokemonAbilities
import com.gdlkug.poke.core.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonAbilitiesUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(idOrName: String): Either<PokemonException, PokemonAbilities> =
        pokemonRepository.getPokemonAbilitiesByIdOrName(idOrName)
}
