package com.sultanseidov.nestedrecyclerview.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sultanseidov.nestedrecyclerview.R
import com.sultanseidov.nestedrecyclerview.listener.ClickReporter
import com.sultanseidov.nestedrecyclerview.model.Order
import com.sultanseidov.nestedrecyclerview.model.Product

class ParentRecyclerAdapter(var orderList: List<Order>, var clickReporter: ClickReporter): RecyclerView.Adapter<ParentRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val sectionSellerNameTextView: TextView = itemView.findViewById(R.id.section_row_seller_name)
        val sectionSubtotal: TextView = itemView.findViewById(R.id.section_row_subtotal)
        val childRecyclerViewContainer: RecyclerView = itemView.findViewById(R.id.rv_child_item_row)

        init {
            childRecyclerViewContainer.setOnClickListener(this)
            sectionSellerNameTextView.setOnClickListener { view ->
                onClick(view)
            }
            sectionSubtotal.setOnClickListener { view ->
                onClick(view)
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            Log.i("NOTE:PRA#onClick","In PRA's VH#onClick w/ pos=${adapterPosition}, clickedView=$p0")
            clickReporter.onNestedRvClick("Parent", adapterPosition, p0)
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // This function should inflate the section_row layout
        // Get layout inflater and inflate the section_row layout
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.section_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // We want to get a section from the sectionList based on position
        val order: Order = orderList[position]
        val sectionRowSellerName: String = order.sellerName
        val products: List<Product> = order.productsInOrder
        val sectionRowSubtotal = order.subTotal

        // Next set text in the Holder for orderSellerName and orderSubtotal
        holder.sectionSellerNameTextView.text = sectionRowSellerName
        holder.sectionSubtotal.text = sectionRowSubtotal.toString()

        // Create a ChildRecyclerAdapter
        val childRecyclerAdapter: ChildRecyclerAdapter = ChildRecyclerAdapter(products, clickReporter)

        // Set adapter:
        holder.childRecyclerViewContainer.adapter = childRecyclerAdapter
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

}