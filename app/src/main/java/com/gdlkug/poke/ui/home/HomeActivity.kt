package com.gdlkug.poke.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.gdlkug.poke.domain.model.Book

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            println("Creando un libro válido...")
            val book1 =
                Book(id = 1, title = "Kotlin Essentials", author = "John Doe", code = "AB12")
            println("Libro válido creado: $book1")
        } catch (e: IllegalArgumentException) {
            println("Error al crear book1: ${e.message}")
        }

        try {
            println("\nCreando un libro con código inválido...")
            val book2 =
                Book(id = 2, title = "Advanced Kotlin", author = "Jane Smith", code = "XYZZ")
            println("Libro válido creado: $book2")
        } catch (e: IllegalArgumentException) {
            println("Error al crear book2: ${e.message}")
        }

        try {
            println("\nCreando otro libro con código inválido...")
            val book3 =
                Book(id = 3, title = "Clean Code", author = "Robert C. Martin", code = "12AA")
            println("Libro válido creado: $book3")
        } catch (e: IllegalArgumentException) {
            println("Error al crear book3: ${e.message}")
        }
    }
}
