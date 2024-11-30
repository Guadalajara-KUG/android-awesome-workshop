package com.gdlkug.poke.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.gdlkug.poke.R

@Composable
fun FavoriteButton() {
    Icon(
        tint = MaterialTheme.colorScheme.primary,
        painter = painterResource(id = R.drawable.ic_star_fill),
        contentDescription = Icons.Filled.Star.name,
    )
}

@Preview
@Composable
fun FavoriteButtonPreview() {
    FavoriteButton()
}
