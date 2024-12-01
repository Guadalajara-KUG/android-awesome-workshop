package com.gdlkug.poke.data.implementations

import com.gdlkug.poke.data.repository.ProductRepository
import com.gdlkug.poke.domain.model.Product

class ProductRepositoryImpl() : ProductRepository {
    override fun getProducts(): List<Product> {
        return listOf(
            Product(1, "Laptop", 1200.0),
            Product(2, "Phone", 800.0),
            Product(3, "Tablet", 500.0)
        )
    }
}