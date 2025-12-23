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

    // Данные из репозитория
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

    // Состояние UI
    var searchQuery by mutableStateOf("")
    var selectedCategory by mutableStateOf("Все")
    var categories by mutableStateOf(listOf("Все"))

    // Фильтрация списка товаров
    val filteredProducts by derivedStateOf {
        val products = allProducts.value
        products.filter { product ->
            // Фильтр по категории
            val categoryMatch = if (selectedCategory == "Все") true else {
                // В реальном приложении лучше использовать ID категорий
                if (selectedCategory == "Мужчинам") product.category.contains("Мужская")
                else if (selectedCategory == "Женщинам") product.category.contains("Женская")
                else true
            }

            // Фильтр по поиску
            val searchMatch = if (searchQuery.isBlank()) true else {
                product.title.contains(searchQuery, ignoreCase = true)
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
                categories = mainRepository.getCategories()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading categories", e)
            }
        }
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
    }

    fun onAddToCart(productId: Int) {
        Log.d("HomeViewModel", "Add to cart: $productId")
        // Логика добавления в корзину
    }
}