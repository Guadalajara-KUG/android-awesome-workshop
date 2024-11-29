package com.gdlkug.poke.domain

import com.gdlkug.poke.data.model.Pokedex
import com.gdlkug.poke.data.repository.PokedexRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetPokedexListUseCase
    @Inject
    constructor(
        private val pokedexRepositoryImpl: PokedexRepositoryImpl,
    ) {
        operator fun invoke(): Flow<Result<Pokedex>> = pokedexRepositoryImpl.observePokedex()
    }
