package com.gdlkug.poke.data.errors

class PokemonException(
    override val message: String?,
    val code: Int
) : Exception(message)
