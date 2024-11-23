package com.jsomven.pokedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jsomven.pokedex.core.domain.model.PokemonPreview

@Composable
fun PokedexList(
    pokemons: List<PokemonPreview>,
    onItemClicked: (PokemonPreview) -> Unit,
    onLargeItemClicked: (PokemonPreview) -> Unit,
    itemsByRow: Int = 3
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.padding(16.dp),
        columns = StaggeredGridCells.Fixed(itemsByRow),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp
    ) {
        items(pokemons) { pokemon ->
            PokemonItem(pokemon, onItemClicked, onLargeItemClicked)
        }
    }
}
