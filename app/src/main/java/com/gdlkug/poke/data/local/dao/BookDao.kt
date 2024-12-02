package com.gdlkug.poke.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.gdlkug.poke.data.local.entity.BookEntity
import com.gdlkug.poke.data.local.entity.BookWithAuthor
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun insertBook(book: BookEntity)

    @Transaction
    @Query("SELECT * FROM book_table")
    fun getBooksWithAuthors(): Flow<List<BookWithAuthor>>

    @Query("DELETE FROM book_table")
    suspend fun deleteAllBooks()
}