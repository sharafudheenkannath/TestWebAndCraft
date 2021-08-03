package com.example.webncrafttest.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

        setupArguments()
        setupUI()
    }

    /**
     * This method used set the layout xml of an activity
     * You don't need to call this method on activity
     */
    abstract fun getLayoutId(): Int

    /**
     * This method should be used to initialize and setup the views
     * You don't need to call this method from the activity's onCreate method, it will call on this base class's onCreate
     */
    abstract fun setupUI()

    /**
     * This method should be used to setup the LiveData observers from the respective ViewModel of the Activity
     * You should also call this method from the onCreate method
     */
    abstract fun setupObservers()

    /**
     * This method used to get arguments from incoming intent
     * You don't need to call this from your activity, it already called from base activity
     */
    abstract fun setupArguments()

    /**
     * Show a toast message
     * @param message   the toast message string
     */
    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}