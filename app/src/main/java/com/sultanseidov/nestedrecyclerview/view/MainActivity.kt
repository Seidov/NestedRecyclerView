package com.sultanseidov.nestedrecyclerview.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sultanseidov.nestedrecyclerview.adapter.ParentRecyclerAdapter
import com.sultanseidov.nestedrecyclerview.databinding.ActivityMainBinding
import com.sultanseidov.nestedrecyclerview.listener.ClickReporter
import com.sultanseidov.nestedrecyclerview.listener.RecyclerItemClickListener
import com.sultanseidov.nestedrecyclerview.model.Order
import com.sultanseidov.nestedrecyclerview.model.Product


class MainActivity : AppCompatActivity() {

    lateinit var listOfOrders: List<Order>
    lateinit var clickReporter: ClickReporter
    private lateinit var binding: ActivityMainBinding
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup Click Reporter
        clickReporter = object: ClickReporter() {
            override fun onResult(parentPos: Int, childPos: Int, viewClicked: View?) {
                Log.i("MainActivity","Parent Position: $parentPos, Child Position: $childPos, viewClicked: $viewClicked")
            }
        }


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Touch Listeners for each Section in Parent RecyclerView:
        binding.rvParentInMainActivityLayout.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(v: View?, position: Int) {
                        Log.i("NOTE:MA#onItemClick","In onItemClick w/ section pos=$position, view=$v")
                        clickReporter.onNestedRvItemClick(position)
                    }
                }
            )
        )

        // Generate listOfOrders to use for testing RecyclerView
        listOfOrders = generateDataforRecyclerViewTest()


        // Get main Recycler View
        val parentRecyclerView = binding.rvParentInMainActivityLayout

        // Create instance on ParentRecyclerAdapter
        val parentRecyclerAdapter: ParentRecyclerAdapter = ParentRecyclerAdapter(listOfOrders,clickReporter)
        // Set the adapter to mainRecyclerView
        parentRecyclerView.adapter = parentRecyclerAdapter

        // Last Step, we need to attach a LayoutManager. Done in layout file.


        // Add Grand Total
        val grandTotalTextView: TextView = binding.tvGrandTotalInMainActivityLayout

        val grandTotal: Int = listOfOrders.map { it.subTotal }.sum()

        grandTotalTextView.setText("Grand Total: $${grandTotal}")


    }

    // Generate a List of Order objects for use in testing Nested RecyclerView
    fun generateDataforRecyclerViewTest(): List<Order> {
        Log.i("NOTE:MA#gdfrvt","In generateDataforRecyclerViewTest")
        // Create 3 instances of Product object to use in Order object
        val apples = Product("Apples", 3, 2)
        val oranges = Product("Oranges", 4, 3)
        val pears = Product("Pears", 4, 5)

        // Generate 2 Object instances using these Product instances.
        val applesSubtotal = apples.priceOfProduct * apples.quantityOfProduct
        val orangesSubtotal = oranges.priceOfProduct * oranges.quantityOfProduct
        val pearsSubtotal = pears.priceOfProduct * pears.quantityOfProduct
        val order1 = Order("Seller #1", listOf(apples, oranges), applesSubtotal + orangesSubtotal)
        val order2 = Order("Seller #2", listOf(oranges, pears), orangesSubtotal + pearsSubtotal)

        return listOf(order1,order2)
    }
}
