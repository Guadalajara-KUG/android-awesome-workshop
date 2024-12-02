package com.gdlkug.poke.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.room.Room
import com.gdlkug.poke.data.local.database.LibraryDatabase
import com.gdlkug.poke.data.local.entity.BookWithAuthor
import com.gdlkug.poke.data.repository.LibraryRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            LibraryDatabase::class.java, "library-database"
        ).build()

        val libraryRepository = LibraryRepository(
            bookDao = db.bookDao(),
            authorDao = db.authorDao()
        )

        val viewModel: HomeViewModel by lazy {
            HomeViewModel(
                repository = libraryRepository
            )
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.booksWithAuthors.collect(::printBookWithAuthor)
                }
            }
        }

        viewModel.addAuthor("J.K. Rowling")
        viewModel.addBook("Harry Potter", 1)
    }

    private fun printBookWithAuthor(list: List<BookWithAuthor>) {
        list.forEach {
            println("Book: ${it.book.title}, Author: ${it.author.name}")
        }
    }
}
