package com.sultanseidov.nestedrecyclerview.model


class Order(
    val sellerName: String = "",
    val productsInOrder: List<Product> = listOf(),
    val subTotal: Int = 0
) {
    override fun toString(): String {
        return "Seller:$sellerName, Products:$productsInOrder, SubTotal:$subTotal"
    }
}