package com.example.crud.repository

import com.example.crud.model.CartModel

class CartRepositoryImpl:CartRepository {
    override fun addToCart(cartModel: CartModel, callback: (Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun updateCart(
        cartId: String,
        data: MutableMap<String, Any>,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteCartById(cartId: String, callback: (Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun deleteAllCart(callback: (List<CartModel>, Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getCartById(cartId: String, callback: (CartModel?, Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getAllCart(callback: (List<CartModel>, Boolean, String) -> Unit) {
        TODO("Not yet implemented")
    }
}