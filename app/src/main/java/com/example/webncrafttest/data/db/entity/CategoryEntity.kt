package com.example.webncrafttest.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val _id: Long,
    @ColumnInfo(name = "category_title") val categoryTitle: String
)