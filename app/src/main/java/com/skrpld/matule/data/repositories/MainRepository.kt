package com.skrpld.matule.data.repositories

import com.skrpld.matule.data.models.Product
import com.skrpld.matule.data.models.Promotion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainRepository() {

    // Имитация базы данных или API для акций
    private val _promotions = MutableStateFlow<List<Promotion>>(
        listOf(
            Promotion(1, "Шорты\nВторник", "4000 ₽", "", 0xFF8BE2E0), // Цвет с картинки (примерный)
            Promotion(2, "Рубашки\nВоскресенье", "8000 ₽", "", 0xFF76A9FF)
        )
    )
    val promotions: StateFlow<List<Promotion>> = _promotions.asStateFlow()

    // Имитация базы данных или API для товаров
    private val _products = MutableStateFlow<List<Product>>(
        listOf(
            Product(1, "Рубашка Воскресенье для машинного вязания", "Мужская одежда", "300 ₽", ""),
            Product(2, "Рубашка Воскресенье для машинного вязания", "Мужская одежда", "300 ₽", ""),
            Product(3, "Шорты для бега", "Женская одежда", "1200 ₽", ""),
            Product(4, "Кепка спортивная", "Мужская одежда", "800 ₽", "")
        )
    )
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    suspend fun getCategories(): List<String> {
        return listOf("Все", "Женщинам", "Мужчинам", "Детям")
    }
}