package com.example.webncrafttest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.webncrafttest.data.db.dao.ProductsDao
import com.example.webncrafttest.data.db.entity.CategoryEntity
import com.example.webncrafttest.data.constants.AppConstants
import com.example.webncrafttest.data.db.entity.ProductEntity

/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
@Database(
    entities = [
        CategoryEntity::class, ProductEntity::class
    ], version = 1, exportSchema = false
)

abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductsDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database
        private fun buildDatabase(context: Context): AppRoomDatabase {

            return Room.databaseBuilder(
                context,
                AppRoomDatabase::class.java,
                AppConstants.DATABASE_NAME
            ).build()
        }

    }
}