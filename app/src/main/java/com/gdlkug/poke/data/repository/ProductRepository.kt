package com.gdlkug.poke.data.repository

import com.gdlkug.poke.domain.model.Product

interface ProductRepository {
    fun getProducts(): List<Product>
}