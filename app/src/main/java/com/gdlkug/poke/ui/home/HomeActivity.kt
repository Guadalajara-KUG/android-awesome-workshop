package com.gdlkug.poke.ui.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gdlkug.poke.data.implementations.ProductRepositoryImpl
import com.gdlkug.poke.data.implementations.UserRepositoryImpl
import com.gdlkug.poke.domain.model.Product
import com.gdlkug.poke.domain.model.User
import com.gdlkug.poke.domain.model.UserWithProducts
import com.gdlkug.poke.domain.usecase.GetProductsUseCase
import com.gdlkug.poke.domain.usecase.GetUserDetailsUseCase
import com.gdlkug.poke.domain.usecase.GetUserWithProductsUseCase
import com.gdlkug.poke.ui.theme.PokedexTheme
import kotlinx.coroutines.launch


class HomeActivity : ComponentActivity() {
    private val productsUseCase = GetProductsUseCase(ProductRepositoryImpl())
    private val userDetailUseCase = GetUserDetailsUseCase(UserRepositoryImpl())
    private val userWithProductsUseCase = GetUserWithProductsUseCase(
        getProductsUseCase = productsUseCase,
        getUserDetailsUseCase = userDetailUseCase
    )

    private val viewModel: HomeViewModel by lazy {
        HomeViewModel(
            getProductsUseCase = productsUseCase,
            getUserDetailsUseCase = userDetailUseCase,
            getUserWithProductsUseCase = userWithProductsUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()

        setContent {
            PokedexTheme {
                viewModel.fetchProducts()
                viewModel.fetchUserDetails(1)
                viewModel.fetchUserWithProducts(1)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userWithProducts.collect(::printUserWithProducts)
                }

                launch {
                    viewModel.products.collect(::printProducts)
                }

                launch {
                    viewModel.userDetails.collect(::printUser)
                }
            }
        }
    }

    fun printUserWithProducts(userWithProducts: UserWithProducts?) {
        userWithProducts?.let { (user, products) ->
            println("User: $user")
            println("Products: $products")
        }
    }

    fun printUser(user: User?) {
        user?.let {
            println("User Details: $it")
        }
    }

    fun printProducts(listOfProducts: List<Product>) {
        listOfProducts.forEach {
            println("User Details: $it")
        }
    }
}
