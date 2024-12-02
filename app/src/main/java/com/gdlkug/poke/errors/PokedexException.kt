package com.gdlkug.poke.errors

class PokedexException(
    override val message: String?,
    val code: Int
) : Exception(message)
