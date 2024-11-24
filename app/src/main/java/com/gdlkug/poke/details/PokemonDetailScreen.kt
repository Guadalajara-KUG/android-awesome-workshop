package com.gdlkug.poke.details

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
import com.gdlkug.poke.abilities.AbilitiesDetailScreen
import com.gdlkug.poke.components.CustomAlertDialog
import com.gdlkug.poke.components.LoadingComponent
import com.gdlkug.poke.core.domain.model.PokemonPreview
import com.gdlkug.poke.evolutionchain.EvolutionChainScreen
import com.gdlkug.poke.home.screens.HomeScreen

class PokemonDetailScreen(
    @Transient private val pokemon: PokemonPreview
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val pokemonDetailViewModel: PokemonDetailViewModel = getViewModel()
        val pokemonDetailUiState by pokemonDetailViewModel.pokemonDetailUiState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground)
        ) {
            val id = if (pokemon.id == HomeScreen.POKEMON_BY_URL) pokemon.name else pokemon.id.toString()
            pokemonDetailViewModel.getPokemonSpeciesDetailByIdOrName(id)

            when (val state = pokemonDetailUiState) {
                PokemonDetailUiState.Loading -> {
                    LoadingComponent()
                }

                is PokemonDetailUiState.CommunicationError -> {
                    CustomAlertDialog(
                        navigator,
                        state.error.message,
                        state.error.code
                    )
                }

                is PokemonDetailUiState.SpeciesFeed -> {
                    com.gdlkug.poke.components.PokemonDetail(
                        modifier = Modifier.fillMaxSize(),
                        pokemon = state.pokemonSpecies,
                        onEvolutionChainClick = {
                            navigator.push(
                                EvolutionChainScreen(
                                    state.pokemonSpecies.evolutionChain
                                )
                            )
                        },
                        onAbilitiesClick = {
                            navigator.push(
                                AbilitiesDetailScreen(
                                    state.pokemonSpecies.id
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}
