package com.example.webncrafttest.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.webncrafttest.data.db.entity.CategoryEntity
import com.example.webncrafttest.data.db.entity.ProductEntity

/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
@Dao
interface ProductsDao {

    @Insert
    fun insertCategory(category: CategoryEntity): Long

    @Insert
    fun insertProduct(product: ProductEntity)

    @Query("SELECT * FROM categories")
    fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM products WHERE category_id ==:categoryId")
    fun getProducts(categoryId: Long): List<ProductEntity>

    @Query("DELETE FROM categories")
    fun clearAllCategories()

    @Query("DELETE FROM products")
    fun clearAllProducts()


}