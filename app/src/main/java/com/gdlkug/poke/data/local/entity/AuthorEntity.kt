package com.gdlkug.poke.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "author_table")
data class AuthorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)
