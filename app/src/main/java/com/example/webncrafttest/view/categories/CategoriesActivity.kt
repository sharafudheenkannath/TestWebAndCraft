package com.example.webncrafttest.view.categories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.webncrafttest.R
import com.example.webncrafttest.base.BaseActivity
import com.example.webncrafttest.data.networkmodel.NetworkResult
import com.example.webncrafttest.di.InjectorUtils
import com.example.webncrafttest.view.categories.adapter.CategoriesAdapter
import com.example.webncrafttest.view.categories.model.Category
import com.example.webncrafttest.view.categories.model.Product
import com.example.webncrafttest.view.loader.LottieDialogFragment
import com.example.webncrafttest.view.productdetail.ProductDetailsActivity
import kotlinx.android.synthetic.main.activity_categories.*

import android.net.ConnectivityManager


/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
class CategoriesActivity : BaseActivity(), CategoriesAdapter.CategoriesAdapterListener {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CategoriesActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var loaderDialog: LottieDialogFragment
    private lateinit var categoriesAdapter: CategoriesAdapter
    private val categoryList = ArrayList<Category>()
    private val viewModel: CategoriesViewModel by viewModels {
        InjectorUtils.provideCategoriesViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loaderDialog = LottieDialogFragment.newInstance()
        setupObservers()

        viewModel.getCategoriesAndProducts(isNetworkAvailable())
    }

    override fun getLayoutId(): Int = R.layout.activity_categories

    override fun setupUI() {
        categoriesAdapter = CategoriesAdapter(this, categoryList, this)
        rvCategories.adapter = categoriesAdapter
    }

    override fun setupObservers() {
        viewModel.categoriesResult.observe(this, Observer {
            when (it.status) {
                NetworkResult.Status.LOADING -> {
                    loaderDialog.show(supportFragmentManager, "")
                }
                NetworkResult.Status.SUCCESS -> {
                    try {
                        loaderDialog.dismiss()
                    } catch (e: IllegalStateException) {
                        e.printStackTrace()
                    }
                    if (it.data == null) {
                        showToast("Null data found")
                    } else {
                        if (it.data.status) {
                            categoryList.clear()
                            categoryList.addAll(it.data.categories)
                            categoriesAdapter.notifyDataSetChanged()
                        } else {
                            showToast(it.data.msg)
                        }
                    }
                }
                NetworkResult.Status.ERROR -> {
                    loaderDialog.dismiss()
                    showToast(it.message!!)
                }

            }
        })
    }

    override fun setupArguments() {
    }

    override fun onProductItemClicked(data: Product) {
        ProductDetailsActivity.start(this, data)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}