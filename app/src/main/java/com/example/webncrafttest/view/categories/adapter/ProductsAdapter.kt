package com.example.webncrafttest.view.categories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.webncrafttest.R
import com.example.webncrafttest.view.categories.model.Product
import kotlinx.android.synthetic.main.grid_products.view.*
/**
 * Created by Sharafu
 * Created at 03/08/2021
 */
class ProductsAdapter(
    private val context: Context,
    private val productList: ArrayList<Product>,
    private val listener: ProductsAdapterListener
) :
    RecyclerView.Adapter<ProductsAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val view = itemView
        fun bind(data: Product, itemPosition: Int) {
            view.apply {
                Glide.with(context).load(data.imageUrl).into(this.ivProductImage)
                tvProductNameGrid.text = data.title
                tvProductPriceGrid.text =
                    context.resources.getString(R.string.price_label, data.price.toString())

                setOnClickListener {
                    listener.onItemClicked(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.grid_products, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(productList[position], position)
    }

    override fun getItemCount(): Int = productList.size

    interface ProductsAdapterListener {
        fun onItemClicked(data: Product)
    }
}