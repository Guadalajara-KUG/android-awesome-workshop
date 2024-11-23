package com.jsomven.pokedex.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import com.jsomven.pokedex.R

@Composable
fun CustomAlertDialog(navigator: Navigator, message: String?, code: Int) {
    AlertDialog(
        onDismissRequest = {
            navigator.pop()
        },
        title = {
            Text(
                text = stringResource(id = R.string.ups)
            )
        },
        text = {
            Text(
                "$message" +
                    "$code"
            )
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = {
                navigator.pop()
            }) {
                Text(
                    text = stringResource(id = R.string.dismiss)
                )
            }
        }
    )
}
