package com.gdlkug.poke.core.domain.errors

class PokemonException(
    override val message: String?,
    val code: Int
) : Exception(message)
