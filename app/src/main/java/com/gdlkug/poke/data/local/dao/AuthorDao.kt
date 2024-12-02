package com.gdlkug.poke.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gdlkug.poke.data.local.entity.AuthorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorDao {

    @Insert
    suspend fun insertAuthor(author: AuthorEntity)

    @Query("SELECT * FROM author_table")
    fun getAllAuthors(): Flow<List<AuthorEntity>>

}