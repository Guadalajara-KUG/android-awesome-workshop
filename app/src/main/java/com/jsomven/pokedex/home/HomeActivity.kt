package com.jsomven.pokedex.home

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.Navigator
import com.jsomven.pokedex.core.domain.model.PokemonPreview
import com.jsomven.pokedex.home.screens.HomeScreen
import com.jsomven.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private var speciesName: String? = null
    private var splashScreenStays = true
    private val delayTime = TIME_SPLASH_SCREEN

    private val pokemonByUrl by lazy {
        speciesName?.let { name ->
            PokemonPreview(
                id = HomeScreen.POKEMON_BY_URL,
                name = name
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data: Uri? = intent.data
        if (data != null) {
            speciesName = data.getQueryParameter("name")
        }

        installSplashScreen().setKeepOnScreenCondition { splashScreenStays }
        Handler(Looper.getMainLooper()).postDelayed({ splashScreenStays = false }, delayTime)

        setContent {
            PokedexTheme {
                Navigator(HomeScreen(pokemonByUrl))
            }
        }
    }

    companion object {
        private const val TIME_SPLASH_SCREEN = 5000L
    }
}
