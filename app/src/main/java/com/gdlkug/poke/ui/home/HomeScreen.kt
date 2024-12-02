package com.gdlkug.poke.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gdlkug.poke.data.model.PokemonPreview
import com.gdlkug.poke.errors.PokedexException
import com.gdlkug.poke.ui.components.CustomAlertDialog
import com.gdlkug.poke.ui.components.LoadingComponent
import com.gdlkug.poke.ui.components.PokedexList
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class HomeScreen(
    @Transient private var pokemonByUrl: PokemonPreview?,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeViewModel: HomeViewModel = getViewModel()
        val homeUiState by homeViewModel.homeUiState.collectAsStateWithLifecycle()

        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onBackground),
        ) {
            when (val state = homeUiState) {
                HomeUiState.Loading -> {
                    LoadingComponent()
                }

                is HomeUiState.CommunicationError -> {
                    CustomAlertDialog(
                        navigator,
                        PokedexException.message,
                        PokedexException.code,
                    )
                }

                is HomeUiState.PokedexFeed -> {
                    PokedexList(
                        pokemons = state.pokedex.pokemonList,
                        onItemClicked = {
                            TODO("What to do on click")
                        },
                        onLargeItemClicked = {
                            homeViewModel.markPokemonAsFavorite(it)
                        },
                    )
                }
            }
        }
    }

    companion object {
        const val POKEMON_BY_URL = -1000
    }
}
