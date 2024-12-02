package com.gdlkug.poke.errors

class PokemonException(
    override val message: String?,
    val code: Int
) : Exception(message)
