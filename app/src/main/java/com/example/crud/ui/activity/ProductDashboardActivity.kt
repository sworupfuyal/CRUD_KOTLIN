package com.example.crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.R
import com.example.crud.adapter.ProductAdapter
import com.example.crud.databinding.ActivityProductDashboardBinding
import com.example.crud.repository.ProductRepositoryImpl
import com.example.crud.viewmodel.ProductViewModel

class ProductDashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityProductDashboardBinding

    lateinit var productViewModel:ProductViewModel
    lateinit var adapter:ProductAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding=ActivityProductDashboardBinding.inflate(layoutInflater)



        setContentView(binding.root)


        var repo =ProductRepositoryImpl()
            productViewModel=ProductViewModel(repo)
            adapter=ProductAdapter(this@ProductDashboardActivity,ArrayList())

            productViewModel.getAllProducts()
            productViewModel.AllProducts.observe(this){
                it?.let{
                    adapter.updateData(it)
                }
            }
            binding.recyclerView.adapter=adapter
            binding.recyclerView.layoutManager=LinearLayoutManager(this)

        productViewModel.loading.observe(this){loading->
            if(loading){
                binding.progressBar.visibility= View.GONE
            }
        }


        binding.floatingButton.setOnClickListener{
            val intent = Intent(this@ProductDashboardActivity,AddProductActivity::class.java)
            startActivity(intent)
        }


        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                var productId=adapter.getProductId(viewHolder.adapterPosition)
                productViewModel.deleteProduct(productId){
                    success,message->
                    if(success){
                        Toast.makeText(this@ProductDashboardActivity,
                            message,Toast.LENGTH_SHORT).show()

                    }else {
                        Toast.makeText(this@ProductDashboardActivity,
                            message,Toast.LENGTH_SHORT).show()                    }
                }
            }


        }).attachToRecyclerView(binding.recyclerView)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}