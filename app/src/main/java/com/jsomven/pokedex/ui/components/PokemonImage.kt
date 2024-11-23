package com.jsomven.pokedex.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun PokemonImage(modifier: Modifier, pokemonName: String, pokemonUrlImage: String) {
    AsyncImage(
        model = pokemonUrlImage,
        contentDescription = pokemonName,
        modifier = modifier
    )
}
