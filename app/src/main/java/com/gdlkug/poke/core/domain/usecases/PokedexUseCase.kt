package com.gdlkug.poke.core.domain.usecases

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokedexUseCase @Inject constructor(
    private val pokedexRepository: com.gdlkug.poke.core.domain.repository.PokedexRepository
) {
    suspend fun getPokedexList(): Either<com.gdlkug.poke.core.domain.errors.PokedexException, com.gdlkug.poke.core.domain.model.Pokedex> =
        pokedexRepository.getPokedexList()

    suspend fun getPokedexRoomList(): Either<com.gdlkug.poke.core.domain.errors.PokedexException, Flow<List<com.gdlkug.poke.core.domain.model.PokemonPreview>>> =
        pokedexRepository.getPokedexRoomList()

    suspend fun insertPokemonListToRoom(pokemonList: List<com.gdlkug.poke.core.domain.model.PokemonPreview>): Either<com.gdlkug.poke.core.domain.errors.PokedexException, List<Long>> =
        pokedexRepository.insertPokemonListToRoom(pokemonList)

    suspend fun markPokemonAsFavorite(pokemon: com.gdlkug.poke.core.domain.model.PokemonPreview): Either<com.gdlkug.poke.core.domain.errors.PokedexException, Long> =
        pokedexRepository.markPokemonAsFavorite(pokemon)
}
