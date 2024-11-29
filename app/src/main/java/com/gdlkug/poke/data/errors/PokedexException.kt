package com.gdlkug.poke.data.errors

class PokedexException(
    override val message: String?,
    val code: Int
) : Exception(message)
