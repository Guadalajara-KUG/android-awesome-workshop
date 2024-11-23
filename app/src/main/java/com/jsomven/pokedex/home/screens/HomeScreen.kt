package com.jsomven.pokedex.home.screens

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
import com.jsomven.pokedex.core.domain.model.PokemonPreview
import com.jsomven.pokedex.details.PokemonDetailScreen
import com.jsomven.pokedex.home.HomeUiState
import com.jsomven.pokedex.home.HomeViewModel
import com.jsomven.pokedex.ui.components.CustomAlertDialog
import com.jsomven.pokedex.ui.components.LoadingComponent
import com.jsomven.pokedex.ui.components.PokedexList

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
                    CustomAlertDialog(navigator, state.error.message, state.error.code)
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
