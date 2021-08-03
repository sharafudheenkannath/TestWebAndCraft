package com.example.webncrafttest.view.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.webncrafttest.data.repository.AppRepo
/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
class CategoriesViewModelFactory(private val repo: AppRepo) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(repo) as T
    }

}