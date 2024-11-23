package com.jsomven.pokedex.abilities

import android.annotation.SuppressLint
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
import com.jsomven.pokedex.ui.components.CustomAlertDialog
import com.jsomven.pokedex.ui.components.LoadingComponent
import com.jsomven.pokedex.ui.components.PokemonAbilityItem
import com.jsomven.pokedex.ui.components.PokemonListColumn

class AbilitiesDetailScreen(
    @Transient private val id: Int
) : Screen {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val abilitiesDetailViewModel: AbilitiesDetailViewModel = getViewModel()
        val pokemonAbilitiesUiState by abilitiesDetailViewModel.pokemonAbilitiesUiState.collectAsState()

        if (id != -1) {
            abilitiesDetailViewModel.getPokemonAbilitiesByIdOrName(id.toString())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground),
            verticalArrangement = Arrangement.Center
        ) {
            when (val state = pokemonAbilitiesUiState) {
                PokemonAbilitiesUiState.Loading -> {
                    LoadingComponent()
                }

                is PokemonAbilitiesUiState.CommunicationError -> {
                    CustomAlertDialog(navigator, state.error.message, state.error.code)
                }

                is PokemonAbilitiesUiState.AbilitiesFeed -> {
                    Spacer(modifier = Modifier.weight(1f))
                    PokemonListColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        itemList = state.pokemonAbilities.abilities
                    ) { ability ->
                        PokemonAbilityItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .height(50.dp),
                            ability = ability
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
