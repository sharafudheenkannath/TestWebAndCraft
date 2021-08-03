package com.example.webncrafttest.view.productdetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.webncrafttest.R
import com.example.webncrafttest.base.BaseActivity
import com.example.webncrafttest.view.categories.CategoriesActivity
import com.example.webncrafttest.view.categories.model.Product
import kotlinx.android.synthetic.main.activity_product_details.*
/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
class ProductDetailsActivity : BaseActivity() {

    companion object {
        private const val PARAM_PRODUCT_DATA = "product_item"
        fun start(context: Context, product: Product) {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra(PARAM_PRODUCT_DATA, product)
            context.startActivity(intent)
        }
    }

    private lateinit var productDetails: Product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupObservers()
    }

    override fun getLayoutId(): Int = R.layout.activity_product_details

    override fun setupUI() {

        setSupportActionBar(tbProductDetails)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        tbProductDetails.setNavigationOnClickListener {
            onBackPressed()
        }

        Glide.with(this).load(productDetails.imageUrl).into(ivProductDetails)
        tvProductName.text = productDetails.title
        tvProductPrice.text = getString(R.string.price_label, productDetails.price.toString())
        tvProductDescription.text = productDetails.description
    }

    override fun setupObservers() {
    }

    override fun setupArguments() {
        productDetails = intent.getSerializableExtra(PARAM_PRODUCT_DATA) as Product
    }
}