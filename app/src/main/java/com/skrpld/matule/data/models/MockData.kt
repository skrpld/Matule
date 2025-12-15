package com.skrpld.matule.data.models

data class Promotion(
    val id: Int,
    val title: String,
    val price: String,
    val imageUrl: String,
    val backgroundColor: Long
)

data class Product(
    val id: Int,
    val title: String,
    val category: String,
    val price: String,
    val imageUrl: String
)