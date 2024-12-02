package com.gdlkug.poke.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdlkug.poke.data.local.entity.AuthorEntity
import com.gdlkug.poke.data.local.entity.BookEntity
import com.gdlkug.poke.data.local.entity.BookWithAuthor
import com.gdlkug.poke.data.repository.LibraryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: LibraryRepository
): ViewModel() {

    val booksWithAuthors: Flow<List<BookWithAuthor>> = repository.getBooksWithAuthors()
    val authors: Flow<List<AuthorEntity>> = repository.getAllAuthors()

    fun addBook(title: String, authorId: Int) {
        viewModelScope.launch {
            repository.addBook(BookEntity(title = title, authorId = authorId))
        }
    }

    fun addAuthor(name: String) {
        viewModelScope.launch {
            repository.addAuthor(AuthorEntity(name = name))
        }
    }
}