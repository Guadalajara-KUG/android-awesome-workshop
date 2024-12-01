package com.gdlkug.poke.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdlkug.poke.domain.model.Product
import com.gdlkug.poke.domain.model.User
import com.gdlkug.poke.domain.model.UserWithProducts
import com.gdlkug.poke.domain.usecase.GetProductsUseCase
import com.gdlkug.poke.domain.usecase.GetUserDetailsUseCase
import com.gdlkug.poke.domain.usecase.GetUserWithProductsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserWithProductsUseCase: GetUserWithProductsUseCase
): ViewModel() {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>>
        get() = _products

    private val _userDetails = MutableStateFlow<User?>(null)
    val userDetails: StateFlow<User?>
        get() = _userDetails

    private val _userWithProducts = MutableStateFlow<UserWithProducts?>(null)
    val userWithProducts: StateFlow<UserWithProducts?>
        get() = _userWithProducts

    fun fetchProducts() {
        viewModelScope.launch {
            _products.value = getProductsUseCase()
        }
    }

    fun fetchUserDetails(userId: Int) {
        viewModelScope.launch {
            _userDetails.value = getUserDetailsUseCase(userId)
        }
    }

    fun fetchUserWithProducts(userId: Int) {
        viewModelScope.launch {
            _userWithProducts.value = getUserWithProductsUseCase(userId)
        }
    }
}