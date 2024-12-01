package com.gdlkug.poke.domain.usecase

import com.gdlkug.poke.data.repository.ProductRepository
import com.gdlkug.poke.domain.model.Product

class GetProductsUseCase(private val productRepository: ProductRepository) {
    operator fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}