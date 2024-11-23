package com.jsomven.pokedex.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> PokemonListColumn(
    modifier: Modifier,
    itemList: List<T>,
    itemComposable: @Composable (T) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(itemList) { item ->
            itemComposable(item)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
