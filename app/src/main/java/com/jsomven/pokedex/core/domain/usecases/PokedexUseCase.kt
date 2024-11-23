package com.jsomven.pokedex.core.domain.usecases

import arrow.core.Either
import com.jsomven.pokedex.core.domain.errors.PokedexException
import com.jsomven.pokedex.core.domain.model.Pokedex
import com.jsomven.pokedex.core.domain.model.PokemonPreview
import com.jsomven.pokedex.core.domain.repository.PokedexRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokedexUseCase @Inject constructor(
    private val pokedexRepository: PokedexRepository
) {
    suspend fun getPokedexList(): Either<PokedexException, Pokedex> =
        pokedexRepository.getPokedexList()

    suspend fun getPokedexRoomList(): Either<PokedexException, Flow<List<PokemonPreview>>> =
        pokedexRepository.getPokedexRoomList()

    suspend fun insertPokemonListToRoom(pokemonList: List<PokemonPreview>): Either<PokedexException, List<Long>> =
        pokedexRepository.insertPokemonListToRoom(pokemonList)

    suspend fun markPokemonAsFavorite(pokemon: PokemonPreview): Either<PokedexException, Long> =
        pokedexRepository.markPokemonAsFavorite(pokemon)
}
