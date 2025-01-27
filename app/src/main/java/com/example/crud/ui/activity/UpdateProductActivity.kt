package com.example.crud.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud.R
import com.example.crud.databinding.ActivityAddProductBinding
import com.example.crud.databinding.ActivityUpdateProductBinding
import com.example.crud.model.ProductModel
import com.example.crud.repository.ProductRepositoryImpl
import com.example.crud.viewmodel.ProductViewModel

class UpdateProductActivity : AppCompatActivity() {
    lateinit var binding: ActivityUpdateProductBinding

    lateinit var productViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityUpdateProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var repo =ProductRepositoryImpl()
        productViewModel= ProductViewModel(repo)



//
//        var products:ProductModel?=
//            intent.getParcelableExtra("Products")

        var productId: String=intent.getStringExtra("products").toString()

        productViewModel.getProductById(productId)

        productViewModel.products.observe(this){
            binding.updateName.setText(it?.productName.toString())
            binding.updateDesc.setText(it?.productDesc.toString())
            binding.updatePrice.setText(it?.price.toString().toInt())
        }



//        products.let{
//            binding.updateName.setText(it?.productName.toString())
//            binding.updateDesc.setText(it?.productDesc.toString())
//            binding.updatePrice.setText(it?.price.toString())
//        }

        binding.btnUpdateProduct.setOnClickListener{

            var name =binding.updateName.text.toString()
            var desc =binding.updateDesc.text.toString()
            var price =binding.updatePrice.text.toString()
            var updatedData= mutableMapOf<String,Any>()

            updatedData["productName"]=name
            updatedData["productDesc"]=desc
            updatedData["price"]=price

            productViewModel.updateProduct(productId,updatedData){
                success,message->
                if(success){
                    Toast.makeText(this@UpdateProductActivity,
                        message,
                        Toast.LENGTH_LONG).show()
                    finish()
                }
                else{

                    Toast.makeText(this@UpdateProductActivity,
                        message,
                        Toast.LENGTH_LONG).show()
                }
            }


        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}