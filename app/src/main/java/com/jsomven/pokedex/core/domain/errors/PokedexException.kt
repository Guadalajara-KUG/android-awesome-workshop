package com.jsomven.pokedex.core.domain.errors

class PokedexException(
    override val message: String?,
    val code: Int
) : Exception(message)
