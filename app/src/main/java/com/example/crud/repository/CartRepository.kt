package com.example.crud.repository

import com.example.crud.model.CartModel

interface CartRepository {

    fun addToCart(cartModel: CartModel,
                  callback:(Boolean,String) ->Unit)

    fun updateCart(cartId:String,
                   data:MutableMap<String,Any>,
                   callback: (Boolean, String) -> Unit)

    fun deleteCartById(cartId: String,
                       callback: (Boolean, String) -> Unit)

    fun deleteAllCart(callback: (List<CartModel>,Boolean, String) -> Unit)

    fun getCartById(cartId: String,
                    callback: (CartModel?,Boolean, String) -> Unit)

    fun getAllCart(callback: (List<CartModel>, Boolean, String) -> Unit)
}