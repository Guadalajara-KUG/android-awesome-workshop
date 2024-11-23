package com.jsomven.pokedex.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jsomven.pokedex.core.domain.errors.PokedexException
import com.jsomven.pokedex.core.domain.model.Pokedex
import com.jsomven.pokedex.core.domain.model.PokemonPreview
import com.jsomven.pokedex.core.domain.usecases.PokedexUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokedexUseCase: PokedexUseCase
) : ViewModel() {
    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val homeUiState: StateFlow<HomeUiState> = _homeUiState.asStateFlow()

    private val _roomUiState: MutableStateFlow<RoomUiState> = MutableStateFlow(RoomUiState.Loading)
    val roomUiState: StateFlow<RoomUiState> = _roomUiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val result = pokedexUseCase.getPokedexRoomList()
            result.fold(
                ifLeft = HomeUiState::CommunicationError,
                ifRight = {
                    it.collect { list ->
                        if (list.isEmpty()) {
                            getPokemonListFromNetwork()
                        } else {
                            _homeUiState.value = HomeUiState.PokedexFeed(Pokedex(list))
                        }
                    }
                }
            )
        }
    }

    private fun getPokemonListFromNetwork() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = pokedexUseCase.getPokedexList()
            _homeUiState.value = result.fold(HomeUiState::CommunicationError, HomeUiState::PokedexFeed)
            result.getOrNull()?.let {
                insertPokemonListToRoom(it.pokemonList)
            }
        }
    }

    private fun insertPokemonListToRoom(pokemonList: List<PokemonPreview>) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = pokedexUseCase.insertPokemonListToRoom(pokemonList)
            _roomUiState.value = result.fold(RoomUiState::Error, RoomUiState::Insert)
        }
    }

    fun markPokemonAsFavorite(pokemon: PokemonPreview) {
        viewModelScope.launch {
            pokedexUseCase.markPokemonAsFavorite(pokemon)
        }
    }
}

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class CommunicationError(val error: PokedexException) : HomeUiState()
    data class PokedexFeed(val pokedex: Pokedex) : HomeUiState()
}

sealed class RoomUiState {
    data object Loading : RoomUiState()
    data class Error(val error: PokedexException) : RoomUiState()
    data class Insert(val size: List<Long>) : RoomUiState()
}
