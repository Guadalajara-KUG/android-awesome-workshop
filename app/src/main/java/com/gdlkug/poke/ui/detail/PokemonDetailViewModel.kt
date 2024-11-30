package com.gdlkug.poke.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdlkug.poke.data.errors.PokemonException
import com.gdlkug.poke.data.model.PokemonSpecies
import com.gdlkug.poke.domain.useCase.GetPokemonDetailByIdOrNameUseCase
import com.gdlkug.poke.util.extension.default
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel
    @Inject
    constructor(
        private val getPokemonDetailByIdOrNameUseCase: GetPokemonDetailByIdOrNameUseCase,
    ) : ViewModel() {
        private val _pokemonDetailUiState: MutableStateFlow<PokemonDetailUiState> =
            MutableStateFlow(PokemonDetailUiState.Loading)
        val pokemonDetailUiState: StateFlow<PokemonDetailUiState> = _pokemonDetailUiState.asStateFlow()

        fun getPokemonSpeciesDetailByIdOrName(pokemonId: String) {
            viewModelScope.launch {
                _pokemonDetailUiState.value =
                    getPokemonDetailByIdOrNameUseCase(pokemonId).fold(
                        PokemonDetailUiState::SpeciesFeed,
                        ::handleFailure,
                    )
            }
        }

        private fun handleFailure(exception: Throwable): PokemonDetailUiState {
            val pokemonException = exception as? PokemonException
            return PokemonDetailUiState.CommunicationError(
                pokemonException.default(PokemonException("Unknown error", -1)),
            )
        }
    }

sealed class PokemonDetailUiState {
    data object Loading : PokemonDetailUiState()

    data class CommunicationError(
        val error: PokemonException,
    ) : PokemonDetailUiState()

    data class SpeciesFeed(
        val pokemonSpecies: PokemonSpecies,
    ) : PokemonDetailUiState()
}
