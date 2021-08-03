package com.example.webncrafttest.view.categories

import androidx.lifecycle.ViewModel
import com.example.webncrafttest.data.repository.AppRepo

/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
class CategoriesViewModel(private val repo: AppRepo) : ViewModel() {

    val categoriesResult = repo.categoriesResult

    fun getCategoriesAndProducts(isInternetConnected: Boolean) {
        if (isInternetConnected) {
            repo.getCategories()
        } else {
            repo.getCategoriesFromDb()
        }
    }


}