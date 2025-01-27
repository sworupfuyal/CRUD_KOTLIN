package com.example.crud.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.crud.R
import com.example.crud.model.ProductModel
import com.example.crud.ui.activity.UpdateProductActivity

class ProductAdapter(var context:Context,
    var data :ArrayList<ProductModel>)
    :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()
{

    class ProductViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var productName:TextView =itemView.findViewById(R.id.displayName)
        var btnEdit:TextView =itemView.findViewById(R.id.btnEdit)

        var productPrice:TextView =itemView.findViewById(R.id.displayPrice)
        var productDesc:TextView =itemView.findViewById(R.id.displayDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

       val itemView: View  =  LayoutInflater.from(context).inflate(R.layout.sample_products,parent,false)

      return ProductViewHolder(itemView)



    }

    override fun getItemCount(): Int {
        return data.size


    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {


          holder.productName.text=data[position].productName
           holder.productPrice.text=data[position].price.toString()
           holder.productDesc.text=data[position].productDesc

        holder.btnEdit.setOnClickListener {
            val intent = Intent(context,UpdateProductActivity::class.java)
            intent.putExtra("Products",data[position].productId)
            context.startActivity(intent)
        }


}

    fun updateData(products:List<ProductModel>){
        data.clear()
        data.addAll(products)
        notifyDataSetChanged()
    }

    fun getProductId(position: Int):String{
        return data[position].productId


    }


    }