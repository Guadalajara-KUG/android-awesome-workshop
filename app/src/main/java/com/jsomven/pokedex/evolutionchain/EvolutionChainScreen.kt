package com.jsomven.pokedex.evolutionchain

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.jsomven.pokedex.core.domain.model.EvolutionChainResource
import com.jsomven.pokedex.ui.components.CustomAlertDialog
import com.jsomven.pokedex.ui.components.LoadingComponent
import com.jsomven.pokedex.ui.components.PokemonChainItem
import com.jsomven.pokedex.ui.components.PokemonListColumn

class EvolutionChainScreen(
    @Transient private val evolutionChain: EvolutionChainResource
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val evolutionChainViewModel: EvolutionChainViewModel = getViewModel()
        val evolutionChainUiState by evolutionChainViewModel.evolutionChainUiState.collectAsState()

        if (evolutionChain.name.isNotBlank()) {
            evolutionChainViewModel.getEvolutionChainById(evolutionChain.id)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground),
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = evolutionChainUiState) {
                EvolutionChainUiState.Loading -> {
                    LoadingComponent()
                }

                is EvolutionChainUiState.CommunicationError -> {
                    CustomAlertDialog(navigator, state.error.message, state.error.code)
                }

                is EvolutionChainUiState.EvolutionChainFeed -> {
                    Spacer(modifier = Modifier.weight(1f))
                    PokemonListColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        itemList = state.pokemonSpecies.chain.evolvesTo
                    ) {
                        PokemonChainItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .height(50.dp),
                            pokemon = it
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
