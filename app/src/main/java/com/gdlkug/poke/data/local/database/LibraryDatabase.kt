package com.gdlkug.poke.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gdlkug.poke.data.local.dao.AuthorDao
import com.gdlkug.poke.data.local.dao.BookDao
import com.gdlkug.poke.data.local.entity.AuthorEntity
import com.gdlkug.poke.data.local.entity.BookEntity

@Database(entities = [BookEntity::class, AuthorEntity::class], version = 1, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun authorDao(): AuthorDao
}