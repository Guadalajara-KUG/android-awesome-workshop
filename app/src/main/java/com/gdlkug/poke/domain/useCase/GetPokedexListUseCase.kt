package com.gdlkug.poke.domain.useCase

import com.gdlkug.poke.data.model.Pokedex
import com.gdlkug.poke.data.repository.PokedexRepository
import com.gdlkug.poke.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class GetPokedexListUseCase
    @Inject
    constructor(
        private val pokedexRepositoryImpl: PokedexRepository,
        @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    ) {
        operator fun invoke(): Flow<Result<Pokedex>> = pokedexRepositoryImpl.observePokedex().flowOn(defaultDispatcher)
    }
