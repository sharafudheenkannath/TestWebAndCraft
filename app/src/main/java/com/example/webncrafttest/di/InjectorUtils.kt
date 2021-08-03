package com.example.webncrafttest.di

import android.content.Context
import com.example.webncrafttest.data.db.AppRoomDatabase
import com.example.webncrafttest.data.repository.AppRepo
import com.example.webncrafttest.view.categories.CategoriesViewModelFactory


/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    fun provideCategoriesViewModelFactory(context: Context): CategoriesViewModelFactory {
        return CategoriesViewModelFactory(AppRepo.getInstance(AppRoomDatabase.getInstance(context)))
    }


}