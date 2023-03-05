package com.example.retrofitlessons.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlessons.R
import com.example.retrofitlessons.databinding.ListItemBinding
import com.example.retrofitlessons.retrofit.Product
import com.squareup.picasso.Picasso

class ProductAdapter: ListAdapter<Product,ProductAdapter.Holder >(Comparator()) {

    class  Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ListItemBinding.bind(view)

        fun bind(product: Product) = with(binding){
            title.text = product.title
            desription.text = product.description
            Picasso.get().load(product.images.first()).into(iv)

        }
    }

    class Comparator: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id  == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}