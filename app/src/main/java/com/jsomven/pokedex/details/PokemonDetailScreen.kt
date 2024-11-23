package com.jsomven.pokedex.details

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
import com.jsomven.pokedex.abilities.AbilitiesDetailScreen
import com.jsomven.pokedex.core.domain.model.PokemonPreview
import com.jsomven.pokedex.evolutionchain.EvolutionChainScreen
import com.jsomven.pokedex.home.screens.HomeScreen.Companion.POKEMON_BY_URL
import com.jsomven.pokedex.ui.components.CustomAlertDialog
import com.jsomven.pokedex.ui.components.LoadingComponent
import com.jsomven.pokedex.ui.components.PokemonDetail

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
            val id = if (pokemon.id == POKEMON_BY_URL) pokemon.name else pokemon.id.toString()
            pokemonDetailViewModel.getPokemonSpeciesDetailByIdOrName(id)

            when (val state = pokemonDetailUiState) {
                PokemonDetailUiState.Loading -> {
                    LoadingComponent()
                }

                is PokemonDetailUiState.CommunicationError -> {
                    CustomAlertDialog(navigator, state.error.message, state.error.code)
                }

                is PokemonDetailUiState.SpeciesFeed -> {
                    PokemonDetail(
                        modifier = Modifier.fillMaxSize(),
                        pokemon = state.pokemonSpecies,
                        onEvolutionChainClick = {
                            navigator.push(EvolutionChainScreen(state.pokemonSpecies.evolutionChain))
                        },
                        onAbilitiesClick = {
                            navigator.push(AbilitiesDetailScreen(state.pokemonSpecies.id))
                        }
                    )
                }
            }
        }
    }
}
