package com.jsomven.pokedex.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsomven.pokedex.core.domain.errors.PokemonException
import com.jsomven.pokedex.core.domain.model.PokemonSpecies
import com.jsomven.pokedex.core.domain.usecases.PokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonDetailUseCase: PokemonDetailUseCase,
) : ViewModel() {
    private val _pokemonDetailUiState: MutableStateFlow<PokemonDetailUiState> =
        MutableStateFlow(PokemonDetailUiState.Loading)
    val pokemonDetailUiState: StateFlow<PokemonDetailUiState> = _pokemonDetailUiState.asStateFlow()

    fun getPokemonSpeciesDetailByIdOrName(pokemonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonDetailUiState.value =
                pokemonDetailUseCase(pokemonId).fold(
                    PokemonDetailUiState::CommunicationError,
                    PokemonDetailUiState::SpeciesFeed
                )
        }
    }
}

sealed class PokemonDetailUiState {
    data object Loading : PokemonDetailUiState()
    data class CommunicationError(val error: PokemonException) : PokemonDetailUiState()
    data class SpeciesFeed(val pokemonSpecies: PokemonSpecies) : PokemonDetailUiState()
}
