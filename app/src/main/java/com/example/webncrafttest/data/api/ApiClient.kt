package com.example.webncrafttest.data.api

import com.example.webncrafttest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient private constructor() {
    private var retrofit: Retrofit? = null
    lateinit var apiService: ApiService

    companion object {
        @Volatile
        private var sInstance: ApiClient? = null

        /**
         * Singleton pattern implementation
         */
        val instance: ApiClient?
            get() {
                if (sInstance == null) {
                    synchronized(ApiClient::class.java) { sInstance = ApiClient() }
                }
                return sInstance
            }
    }

    init {
        if (retrofit == null) {

            // HttpLoggingInterceptor
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val firebaseFunctionOkHttpClient = OkHttpClient()
                .newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()
            retrofit = Retrofit.Builder()
                .client(firebaseFunctionOkHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            apiService = retrofit?.create(ApiService::class.java)!!
        }
    }
}