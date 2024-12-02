package com.gdlkug.poke.data.repository

import com.gdlkug.poke.data.local.dao.AuthorDao
import com.gdlkug.poke.data.local.dao.BookDao
import com.gdlkug.poke.data.local.entity.AuthorEntity
import com.gdlkug.poke.data.local.entity.BookEntity
import com.gdlkug.poke.data.local.entity.BookWithAuthor
import kotlinx.coroutines.flow.Flow

class LibraryRepository(
    private val bookDao: BookDao,
    private val authorDao: AuthorDao
) {
    fun getBooksWithAuthors(): Flow<List<BookWithAuthor>> = bookDao.getBooksWithAuthors()

    fun getAllAuthors(): Flow<List<AuthorEntity>> = authorDao.getAllAuthors()

    suspend fun addBook(book: BookEntity) = bookDao.insertBook(book)

    suspend fun addAuthor(author: AuthorEntity) = authorDao.insertAuthor(author)
}