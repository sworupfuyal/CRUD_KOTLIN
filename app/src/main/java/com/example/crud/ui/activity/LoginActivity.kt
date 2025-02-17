package com.example.crud.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud.R
import com.example.crud.databinding.ActivityLoginBinding
import com.example.crud.repository.UserRepositoryImpl
import com.example.crud.utils.LoadingUtils
import com.example.crud.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var userViewModel: UserViewModel
    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)
        loadingUtils = LoadingUtils(this)


        binding.btnSignupnavigate.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }


        binding.btnForget.setOnClickListener {
            val intent = Intent(this@LoginActivity,ForgotActivity::class.java)
            startActivity(intent)
        }



        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()


            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            loadingUtils.show()


            userViewModel.login(email, password) { success, message ->
                loadingUtils.dismiss()
                if (success) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    binding.btnLogin.setOnClickListener {
                        val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                        startActivity(intent)
                    }


                } else {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
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