package com.skrpld.matule.data.repositories

import com.skrpld.matule.data.models.Product
import com.skrpld.matule.data.models.Promotion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainRepository() {
    private val _promotions = MutableStateFlow<List<Promotion>>(
        listOf(
            Promotion(
                id = "1",
                title = "Летняя распродажа",
                price = "от ₽ 500",
                backgroundColor = 0xFF48B2E7
            ),
            Promotion(
                id = "2",
                title = "Новая коллекция",
                price = "от ₽ 1200",
                backgroundColor = 0xFF5B9EE1
            )
        )
    )
    val promotions: StateFlow<List<Promotion>> = _promotions.asStateFlow()

    private val _products = MutableStateFlow<List<Product>>(
        listOf(
            Product(
                id = "101",
                title = "Nike Air Max 270",
                category = "Мужчинам",
                price = "₽ 12 990",
                description = "Легендарные кроссовки с большой вставкой Air."
            ),
            Product(
                id = "102",
                title = "Nike Free Run",
                category = "Популярные",
                price = "₽ 8 490",
                description = "Максимальный комфорт для бега."
            ),
            Product(
                id = "103",
                title = "Nike Joyride Run",
                category = "Женщинам",
                price = "₽ 10 200",
                description = "Технология с тысячами мельчайших шариков."
            ),
            Product(
                id = "104",
                title = "Nike Zoom Fly",
                category = "Детям",
                price = "₽ 6 100",
                description = "Скорость и стиль для юных атлетов."
            )
        )
    )
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    suspend fun getCategories(): List<String> {
        return listOf("Популярные", "Мужчинам", "Женщинам", "Детям", "Аксессуары")
    }

    suspend fun addToCart(productId: String) {
        // TODO: добавление в корзину
    }
}