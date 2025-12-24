package com.skrpld.matule.ui.screens.home

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skrpld.matule.data.repositories.MainRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val promotions = mainRepository.promotions.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private val allProducts = mainRepository.products.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    var searchQuery by mutableStateOf("")
    var selectedCategory by mutableStateOf("Все")
    var categories by mutableStateOf(listOf("Все"))

    val filteredProducts by derivedStateOf {
        val query = searchQuery
        val category = selectedCategory
        val productsList = allProducts.value

        productsList.filter { product ->
            val categoryMatch = if (category == "Все") {
                true
            } else {
                product.category.equals(category, ignoreCase = true) ||
                        product.category.contains(category, ignoreCase = true)
            }

            val searchMatch = if (query.isBlank()) {
                true
            } else {
                product.title.contains(query, ignoreCase = true) ||
                        product.description.contains(query, ignoreCase = true)
            }

            categoryMatch && searchMatch
        }
    }

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            try {
                val remoteCategories = mainRepository.getCategories()
                categories = if (!remoteCategories.contains("Все")) {
                    listOf("Все") + remoteCategories
                } else {
                    remoteCategories
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading categories", e)
                categories = listOf("Все", "Популярные", "Мужчинам", "Женщинам")
            }
        }
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
    }

    fun onAddToCart(productId: String) {
        Log.d("HomeViewModel", "Добавлено в корзину: $productId")
        viewModelScope.launch {
            try {
                mainRepository.addToCart(productId)
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Ошибка при добавлении в корзину", e)
            }
        }
    }
}