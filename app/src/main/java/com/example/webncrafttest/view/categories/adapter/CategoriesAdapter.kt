package com.example.webncrafttest.view.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.webncrafttest.R
import com.example.webncrafttest.view.categories.model.Category
import com.example.webncrafttest.view.categories.model.Product
import kotlinx.android.synthetic.main.row_categories.view.*
/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
class CategoriesAdapter(
    private val context: Context,
    private val categoriesList: ArrayList<Category>,
    private val listener: CategoriesAdapterListener
) : RecyclerView.Adapter<CategoriesAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView),
        ProductsAdapter.ProductsAdapterListener {

        private val view = itemView
        private lateinit var productAdapter: ProductsAdapter

        fun bind(data: Category, itemPosition: Int) {

            if (data.products != null)
                productAdapter = ProductsAdapter(context, data.products as ArrayList<Product>, this)

            view.apply {
                tvCategoryName.text = data.title
                if (data.products != null)
                    rvProducts.adapter = productAdapter

                setOnClickListener {
                    if (rvProducts.visibility == View.VISIBLE) {
                        rvProducts.visibility = View.GONE
                        ivExpandableIndication.rotation = 0F
                    } else {
                        rvProducts.visibility = View.VISIBLE
                        ivExpandableIndication.rotation = 90F
                    }
                }
            }

        }

        override fun onItemClicked(data: Product) {
            listener.onProductItemClicked(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_categories, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(categoriesList[position], position)
    }

    override fun getItemCount(): Int = categoriesList.size

    interface CategoriesAdapterListener {
        fun onProductItemClicked(data: Product)
    }
}