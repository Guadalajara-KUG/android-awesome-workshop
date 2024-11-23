package com.jsomven.pokedex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jsomven.pokedex.R

@Composable
fun <T> PokemonStatsDetail(happiness: Int, captureRate: Int, eggGroups: List<T>) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.happiness, happiness),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.capture_rate, captureRate),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.egg_groups),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = eggGroups.joinToString(" , ", prefix = " ", postfix = " "),
                color = Color.Black
            )
        }
    }
}
