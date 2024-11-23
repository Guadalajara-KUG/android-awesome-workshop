package com.jsomven.pokedex.evolutionchain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsomven.pokedex.core.domain.errors.PokemonException
import com.jsomven.pokedex.core.domain.model.EvolutionChain
import com.jsomven.pokedex.core.domain.usecases.EvolutionChainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EvolutionChainViewModel @Inject constructor(
    private val evolutionChainUseCase: EvolutionChainUseCase
) : ViewModel() {
    private val _evolutionChainUiState: MutableStateFlow<EvolutionChainUiState> =
        MutableStateFlow(EvolutionChainUiState.Loading)
    val evolutionChainUiState: StateFlow<EvolutionChainUiState> = _evolutionChainUiState.asStateFlow()

    fun getEvolutionChainById(chainId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _evolutionChainUiState.value =
                evolutionChainUseCase(chainId).fold(
                    EvolutionChainUiState::CommunicationError,
                    EvolutionChainUiState::EvolutionChainFeed
                )
        }
    }
}

sealed class EvolutionChainUiState {
    data object Loading : EvolutionChainUiState()
    data class CommunicationError(val error: PokemonException) : EvolutionChainUiState()
    data class EvolutionChainFeed(val pokemonSpecies: EvolutionChain) : EvolutionChainUiState()
}
