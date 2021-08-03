package com.example.webncrafttest.view.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.webncrafttest.R
import com.example.webncrafttest.base.BaseActivity
import com.example.webncrafttest.view.categories.CategoriesActivity
/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            CategoriesActivity.start(this)
        }, 1000)
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun setupUI() {

    }

    override fun setupObservers() {

    }

    override fun setupArguments() {

    }
}