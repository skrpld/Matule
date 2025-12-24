package com.skrpld.matule.data.models

data class Product(
    val id: String,
    val title: String,
    val category: String,
    val price: String,
    val description: String = ""
)

data class Promotion(
    val id: String,
    val title: String,
    val price: String,
    val backgroundColor: Long
)