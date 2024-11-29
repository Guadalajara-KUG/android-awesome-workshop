package com.gdlkug.poke.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.gdlkug.poke.R

@Composable
fun PokemonImageNotFound(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.unkwon),
        contentDescription = stringResource(id = R.string.pokemon_not_found),
        modifier = modifier
    )
}