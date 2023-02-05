package com.sultanseidov.nestedrecyclerview.model

class Product(val titleOfProduct: String = "", val priceOfProduct: Int = 0, val quantityOfProduct: Int = 0) {
    override fun toString(): String {
        return "Product Name:$titleOfProduct, Price:$priceOfProduct, Quantity:$quantityOfProduct"
    }
}
