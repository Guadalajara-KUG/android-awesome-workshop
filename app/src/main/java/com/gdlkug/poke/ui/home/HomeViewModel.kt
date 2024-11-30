package com.gdlkug.poke.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdlkug.poke.data.errors.PokedexException
import com.gdlkug.poke.data.model.Pokedex
import com.gdlkug.poke.data.model.PokemonPreview
import com.gdlkug.poke.domain.useCase.GetPokedexListUseCase
import com.gdlkug.poke.domain.useCase.MarkPokemonAsFavoriteUseCase
import com.gdlkug.poke.util.extension.default
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        getPokedexListUseCase: GetPokedexListUseCase,
        private val markPokemonAsFavoriteUseCase: MarkPokemonAsFavoriteUseCase,
    ) : ViewModel() {
        val homeUiState: StateFlow<HomeUiState> =
            getPokedexListUseCase()
                .transform {
                    emit(it.fold(HomeUiState::PokedexFeed, ::handleFailure))
                }.stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    HomeUiState.Loading,
                )

        private fun handleFailure(exception: Throwable): HomeUiState {
            val pokemonException = exception as? PokedexException
            return HomeUiState.CommunicationError(
                pokemonException.default(PokedexException("Unknown error", -1)),
            )
        }

        fun markPokemonAsFavorite(pokemon: PokemonPreview) {
            viewModelScope.launch {
                markPokemonAsFavoriteUseCase(pokemon)
            }
        }

        companion object {
            private const val TIMEOUT_MILLIS = 5000L
        }
    }

sealed class HomeUiState {
    data object Loading : HomeUiState()

    data class CommunicationError(
        val error: PokedexException,
    ) : HomeUiState()

    data class PokedexFeed(
        val pokedex: Pokedex,
    ) : HomeUiState()
}
