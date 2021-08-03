package com.example.webncrafttest.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.webncrafttest.data.api.ApiClient
import com.example.webncrafttest.data.db.AppRoomDatabase
import com.example.webncrafttest.data.db.entity.CategoryEntity
import com.example.webncrafttest.data.db.entity.ProductEntity
import com.example.webncrafttest.data.networkmodel.NetworkResult
import com.example.webncrafttest.view.categories.model.CategoriesResponseDataModel
import com.example.webncrafttest.view.categories.model.Category
import com.example.webncrafttest.view.categories.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class AppRepo(private val roomDb: AppRoomDatabase) {

    companion object {
        fun getInstance(roomDb: AppRoomDatabase) = AppRepo(roomDb)
    }

    val categoriesResult = MutableLiveData<NetworkResult<CategoriesResponseDataModel>>()


    fun getCategories() {

        categoriesResult.postValue(NetworkResult.loading())
        ApiClient.instance?.apiService?.getCategories()
            ?.enqueue(object : Callback<CategoriesResponseDataModel> {
                override fun onResponse(
                    call: Call<CategoriesResponseDataModel>,
                    response: Response<CategoriesResponseDataModel>
                ) {
                    if (response.body() == null) {
                        //categoriesResult.postValue(NetworkResult.error("Null data found"))
                        getCategoriesFromDb()
                    } else {
                        categoriesResult.postValue(NetworkResult.success(response.body()!!))
                        updateLocalDb(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<CategoriesResponseDataModel>, t: Throwable) {
                    Timber.d("getCategories Api error -> ${t.message.toString()}")
                    //categoriesResult.postValue(NetworkResult.error(t.message.toString()))
                    getCategoriesFromDb()
                }

            })
    }

    private fun updateLocalDb(
        response: CategoriesResponseDataModel,
    ) {
        val dao = roomDb.productDao()
        GlobalScope.launch(Dispatchers.IO) {
            dao.clearAllCategories()
            dao.clearAllProducts()
            for (data in response.categories) {
                val category = CategoryEntity(0, data.title)
                val catId = dao.insertCategory(category)

                if (data.products != null) {
                    for (product in data.products) {
                        val productEntity = ProductEntity(
                            0,
                            catId,
                            product.title,
                            product.price,
                            product.imageUrl,
                            product.description
                        )
                        dao.insertProduct(productEntity)
                    }
                }
            }
        }
    }

    fun getCategoriesFromDb() {

        categoriesResult.postValue(NetworkResult.loading())

        val dao = roomDb.productDao()
        val categoryList = ArrayList<Category>()
        GlobalScope.launch(Dispatchers.IO) {
            val categories = dao.getCategories()
            if (categories.isEmpty()) {
                val categoriesResponseDataModel =
                    CategoriesResponseDataModel(categoryList, "No entries found in database", false)
                categoriesResult.postValue(NetworkResult.success(categoriesResponseDataModel))
            }

            for (categoryEntity in categories) {
                val prodList = ArrayList<Product>()
                val products = dao.getProducts(categoryEntity._id)
                for (productEntity in products) {
                    val productModel = Product(
                        productEntity.productDescription,
                        productEntity.productImage,
                        productEntity.productPrice,
                        productEntity.productTitle
                    )
                    prodList.add(productModel)
                }
                val categoryModel = Category(prodList, categoryEntity.categoryTitle)
                categoryList.add(categoryModel)
            }
            val categoriesResponseDataModel = CategoriesResponseDataModel(categoryList, "", true)
            categoriesResult.postValue(NetworkResult.success(categoriesResponseDataModel))
        }
    }


}