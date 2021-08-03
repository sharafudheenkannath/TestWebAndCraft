package com.example.webncrafttest.view.categories.model

import java.io.Serializable

data class CategoriesResponseDataModel(
    val categories: List<Category>,
    val msg: String,
    val status: Boolean
)

data class Category(
    val products: List<Product>? = null,
    val title: String
)

data class Product(
    val description: String,
    val imageUrl: String,
    val price: Int,
    val title: String
) : Serializable