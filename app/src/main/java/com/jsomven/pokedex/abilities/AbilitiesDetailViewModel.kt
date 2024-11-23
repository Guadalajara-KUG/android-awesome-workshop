package com.jsomven.pokedex.abilities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsomven.pokedex.core.domain.errors.PokemonException
import com.jsomven.pokedex.core.domain.model.PokemonAbilities
import com.jsomven.pokedex.core.domain.usecases.PokemonAbilitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilitiesDetailViewModel @Inject constructor(
    private val pokemonAbilitiesUseCase: PokemonAbilitiesUseCase
) : ViewModel() {
    private val _pokemonAbilitiesUiState: MutableStateFlow<PokemonAbilitiesUiState> =
        MutableStateFlow(PokemonAbilitiesUiState.Loading)
    val pokemonAbilitiesUiState: StateFlow<PokemonAbilitiesUiState> = _pokemonAbilitiesUiState.asStateFlow()

    fun getPokemonAbilitiesByIdOrName(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonAbilitiesUiState.value =
                pokemonAbilitiesUseCase(id).fold(
                    PokemonAbilitiesUiState::CommunicationError,
                    PokemonAbilitiesUiState::AbilitiesFeed
                )
        }
    }
}

sealed class PokemonAbilitiesUiState {
    data object Loading : PokemonAbilitiesUiState()
    data class CommunicationError(val error: PokemonException) : PokemonAbilitiesUiState()
    data class AbilitiesFeed(val pokemonAbilities: PokemonAbilities) : PokemonAbilitiesUiState()
}
