package com.jsomven.pokedex.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.jsomven.pokedex.ui.theme.Colors.DARK_BACKGROUND
import com.jsomven.pokedex.ui.theme.Colors.DARK_PRIMARY
import com.jsomven.pokedex.ui.theme.Colors.DARK_SECONDARY
import com.jsomven.pokedex.ui.theme.Colors.DARK_TERTIARY
import com.jsomven.pokedex.ui.theme.Colors.LIGHT_BACKGROUND
import com.jsomven.pokedex.ui.theme.Colors.LIGHT_PRIMARY
import com.jsomven.pokedex.ui.theme.Colors.LIGHT_SECONDARY
import com.jsomven.pokedex.ui.theme.Colors.LIGHT_TERTIARY

private val DarkColorScheme = darkColorScheme(
    primary = Color(DARK_PRIMARY),
    secondary = Color(DARK_SECONDARY),
    tertiary = Color(DARK_TERTIARY),
    onBackground = Color(DARK_BACKGROUND)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(LIGHT_PRIMARY),
    secondary = Color(LIGHT_SECONDARY),
    tertiary = Color(LIGHT_TERTIARY),
    onBackground = Color(LIGHT_BACKGROUND)
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
