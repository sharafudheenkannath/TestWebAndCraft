package com.example.webncrafttest.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class ProductEntity(
    @PrimaryKey(autoGenerate = true) val _id: Long,
    @ColumnInfo(name = "category_id") val categoryId: Long,
    @ColumnInfo(name = "product_title") val productTitle: String,
    @ColumnInfo(name = "product_price") val productPrice: Int,
    @ColumnInfo(name = "product_image") val productImage: String,
    @ColumnInfo(name = "product_description") val productDescription: String
)