package com.gdlkug.poke.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gdlkug.poke.components.CustomAlertDialog
import com.gdlkug.poke.components.LoadingComponent
import com.gdlkug.poke.core.domain.model.PokemonPreview
import com.gdlkug.poke.details.PokemonDetailScreen
import com.gdlkug.poke.home.HomeUiState
import com.gdlkug.poke.home.HomeViewModel
import com.gdlkug.poke.ui.components.PokedexList

class HomeScreen(
    @Transient private var pokemonByUrl: PokemonPreview?
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val homeViewModel: HomeViewModel = getViewModel()
        val homeUiState by homeViewModel.homeUiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground)
        ) {
            when (val state = homeUiState) {
                HomeUiState.Loading -> {
                    LoadingComponent()
                }

                is HomeUiState.CommunicationError -> {
                    CustomAlertDialog(
                        navigator,
                        state.error.message,
                        state.error.code
                    )
                }

                is HomeUiState.PokedexFeed -> {
                    PokedexList(
                        pokemons = state.pokedex.pokemonList,
                        onItemClicked = {
                            navigator.push(PokemonDetailScreen(it))
                        },
                        onLargeItemClicked = {
                            homeViewModel.markPokemonAsFavorite(it)
                        }
                    )
                }
            }
        }

        pokemonByUrl?.let {
            navigator.push(PokemonDetailScreen(it))
            pokemonByUrl = null
        }
    }

    companion object {
        const val POKEMON_BY_URL = -1000
    }
}
