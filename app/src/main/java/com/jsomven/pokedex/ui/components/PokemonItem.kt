package com.jsomven.pokedex.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jsomven.pokedex.core.domain.model.PokemonPreview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonItem(
    pokemon: PokemonPreview,
    onItemClicked: (PokemonPreview) -> Unit,
    onLargeItemClicked: (PokemonPreview) -> Unit
) {
    Card(
        modifier = Modifier
            .width(100.dp)
            .height(150.dp)
            .combinedClickable(
                onClick = { onItemClicked(pokemon) },
                onLongClick = { onLargeItemClicked(pokemon) },
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (pokemon.id == -1) {
                PokemonImageNotFound(modifier = Modifier.padding(bottom = 16.dp))
            } else {
                PokemonImage(
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .width(100.dp)
                        .height(100.dp),
                    pokemonName = pokemon.name,
                    pokemonUrlImage = pokemon.imageUrl
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pokemon.name,
                    color = Color.Black
                )
                if (pokemon.isFavorite) {
                    Spacer(modifier = Modifier.width(8.dp))
                    FavoriteButton()
                }
            }
        }
    }
}

@Preview
@Composable
fun PokemonItemPreview() {
    PokemonItem(
        pokemon = PokemonPreview(
            id = -1,
            name = "bulbasaur",
            isFavorite = true
        ),
        onItemClicked = {
            println(it)
        },
        onLargeItemClicked = {}
    )
}
