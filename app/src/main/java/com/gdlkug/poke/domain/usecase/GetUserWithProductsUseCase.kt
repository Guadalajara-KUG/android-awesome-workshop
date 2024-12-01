package com.gdlkug.poke.domain.usecase

import com.gdlkug.poke.domain.model.UserWithProducts

class GetUserWithProductsUseCase(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getProductsUseCase: GetProductsUseCase
) {
    operator fun invoke(userId: Int): UserWithProducts {
        val user = getUserDetailsUseCase(userId)
        val products = getProductsUseCase()

        return UserWithProducts(user, products)
    }
}