package com.jsomven.pokedex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jsomven.pokedex.core.domain.model.Species

@Composable
fun PokemonChainItem(modifier: Modifier, pokemon: Species) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        )
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pokemon.name.isBlank()) {
                PokemonImageNotFound(modifier = Modifier.width(50.dp))
            } else {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier.width(50.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name,
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun PokemonChainItemPreview() {
    PokemonChainItem(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        pokemon = Species(
            name = "venasur",
            url = "https://pokeapi.co/api/v2/pokemon/1/"
        )
    )
}
