package com.example.webncrafttest.data.api


import com.example.webncrafttest.view.categories.model.CategoriesResponseDataModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
interface ApiService {

    @GET("/v2/5ec39cba300000720039c1f6")
    fun getCategories(): Call<CategoriesResponseDataModel>

}
