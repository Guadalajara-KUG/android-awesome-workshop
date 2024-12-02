package com.gdlkug.poke.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BookWithAuthor(
    @Embedded
    val book: BookEntity,
    @Relation(
        parentColumn = "authorId",
        entityColumn = "id"
    )
    val author: AuthorEntity
)
