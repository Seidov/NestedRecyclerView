package com.sultanseidov.nestedrecyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sultanseidov.nestedrecyclerview.R
import com.sultanseidov.nestedrecyclerview.listener.ClickReporter
import com.sultanseidov.nestedrecyclerview.model.Product

class ChildRecyclerAdapter(val products: List<Product>, val clickReporter: ClickReporter):
    RecyclerView.Adapter<ChildRecyclerAdapter.ViewHolder>() {


    inner class ViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val singleItemProductNameTextView: TextView = itemView.findViewById(R.id.item_row_product_name)
        val singleItemProductPriceTextView: TextView = itemView.findViewById(R.id.item_row_product_price)
        val singleItemProductQuantityTextView: TextView = itemView.findViewById(R.id.item_row_product_quantity)
        val singleItemUpdateQuantityButton: Button = itemView.findViewById(R.id.btn_update_quantity)

        init {
            singleItemUpdateQuantityButton.setOnClickListener { view ->
                onClick(view)
            }
            singleItemProductNameTextView.setOnClickListener { view ->
                onClick(view)
            }
            singleItemProductPriceTextView.setOnClickListener { view ->
                onClick(view)
            }
            singleItemProductQuantityTextView.setOnClickListener { view ->
                onClick(view)
            }
            itemView.setOnClickListener(this)
        }


        override fun onClick(clickedView: View?) {
            Log.i("NOTE:CRA:onClick","in CRA's VH#onClick w/ pos=$adapterPosition, clickedView=$clickedView")
            clickReporter.onNestedRvClick("Child", adapterPosition, clickedView)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // To create view holder, we need to inflate our item_row
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.item_row, parent, false)
        val updateBtn: Button = view.findViewById(R.id.btn_update_quantity)
        val quantityEditTextView: TextView = view.findViewById(R.id.item_row_product_quantity)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // First get the CartListItem at position "position
        val productItem: Product = products[position]
        // Second, set all the appropriate Views in item_row
        holder.singleItemProductNameTextView.text = productItem.titleOfProduct
        holder.singleItemProductPriceTextView.text = productItem.priceOfProduct.toString()
        holder.singleItemProductQuantityTextView.text = productItem.quantityOfProduct.toString()

    }


    override fun getItemCount(): Int {
        return products.size
    }



}
