package com.gdlkug.poke.domain.model

data class UserWithProducts(
    val user: User,
    val products: List<Product>
)
