package com.example.crud.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud.R
import com.example.crud.databinding.ActivityAddProductBinding
import com.example.crud.databinding.ActivityProductDashboardBinding
import com.example.crud.model.ProductModel
import com.example.crud.repository.ProductRepositoryImpl
import com.example.crud.utils.LoadingUtils
import com.example.crud.viewmodel.ProductViewModel

class AddProductActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddProductBinding
    lateinit var productViewModel: ProductViewModel
    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding =ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils= LoadingUtils(this)
        val repo=ProductRepositoryImpl()
        productViewModel=ProductViewModel(repo)

        binding.btnAddProduct.setOnClickListener {

            var name =binding.pName.text.toString()
            var price =binding.pPrice.text.toString().toInt()
            var desc =binding.pDesc.text.toString()

            var model =ProductModel("",name,desc,price)

            loadingUtils.show()
            productViewModel.addProduct(model){
                    success,message ->
                if (success){
                    Toast.makeText(this@AddProductActivity,
                        message,
                        Toast.LENGTH_LONG).show()
                    loadingUtils.dismiss()
                    finish()
                }else{
                    Toast.makeText(this@AddProductActivity,
                        message,
                        Toast.LENGTH_LONG).show()
                    loadingUtils.dismiss()
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