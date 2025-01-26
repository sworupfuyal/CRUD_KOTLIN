package com.example.crud.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud.R
import com.example.crud.databinding.ActivityForgotBinding
import com.example.crud.repository.UserRepositoryImpl
import com.example.crud.utils.LoadingUtils
import com.example.crud.viewmodel.UserViewModel

class ForgotActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgotBinding
    lateinit var userViewModel: UserViewModel
    lateinit var loadingUtils: LoadingUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize repository, ViewModel, and LoadingUtils
        val repo = UserRepositoryImpl()
        userViewModel = UserViewModel(repo)
        loadingUtils = LoadingUtils(this)

        // Handle Reset Password button click
        binding.btnResetPassword.setOnClickListener {
            val email = binding.forgotPasswordEmail.text.toString()

            // Validate input
            if (email.isEmpty()) {
                Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show loading indicator
            loadingUtils.show()

            // Call forgetPassword method
            userViewModel.forgetPassword(email) { success, message ->
                loadingUtils.dismiss()
                if (success) {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    finish() // Close the activity after success
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                }
            }
        }

        // Handle edge-to-edge system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}