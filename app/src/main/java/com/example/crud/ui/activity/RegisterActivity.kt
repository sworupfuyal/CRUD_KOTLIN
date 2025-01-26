package com.example.crud.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud.R
import com.example.crud.databinding.ActivityRegisterBinding
import com.example.crud.model.userModel
import com.example.crud.repository.UserRepositoryImpl
import com.example.crud.utils.LoadingUtils
import com.example.crud.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var userViewModel: UserViewModel
    lateinit var loadingUtils: LoadingUtils






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUtils=LoadingUtils(this)
        val repo =UserRepositoryImpl()
        userViewModel= UserViewModel(repo)





        binding.signUp.setOnClickListener {
            loadingUtils.show()
            var email = binding.registerEmail.text.toString()
            var password = binding.registerPassword.text.toString()
            var firstName = binding.registerFname.text.toString()
            var lastName = binding.registerLName.text.toString()
            var address = binding.registerAddress.text.toString()
            var contact = binding.registerContact.text.toString()

            userViewModel.register(email, password) { success, message, userId ->
                if (success) {
                    var userModel = userModel(
                        userId.toString(),
                        firstName, lastName, address, email, contact
                    )
                    addUser(userModel)

                } else {
                    Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_LONG).show()

                    loadingUtils.dismiss()



                }
            }



//            auth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener {
//                    if(it.isSuccessful){
//                        var userId =auth.currentUser?.uid
//                        var userModel =userModel(
//                            userId.toString(),
//                            firstName,lastName,address,email,contact
//                        )
//                        reference.child(userId.toString()).setValue(userModel).addOnCompleteListener {
//
//                            if(it.isSuccessful){
//                                Toast.makeText(this@RegisterActivity,
//                                    "Registration Success",
//                                    Toast.LENGTH_LONG).show()
//
//                            } else{
//                                Toast.makeText(this@RegisterActivity,
//                                    it.exception?.message.toString(),
//                                    Toast.LENGTH_LONG).show()
//
//                            }
//                        }
//
//
//                        Toast.makeText(this@RegisterActivity,
//                            "Registration Success",
//                            Toast.LENGTH_LONG).show()
//                    }else{
//                        Toast.makeText(this@RegisterActivity,
//                            it.exception?.message.toString(),
//                            Toast.LENGTH_LONG).show()
//                    }
//                }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun addUser(userModel:userModel){
        userViewModel.addUserToDatabase(userModel.UserId,userModel){
                success,message ->
            if(success){
                Toast.makeText(this@RegisterActivity,
                    message,Toast.LENGTH_LONG).show()
                loadingUtils.dismiss()
            }else{
                Toast.makeText(this@RegisterActivity,
                    message,Toast.LENGTH_LONG).show()
                loadingUtils.dismiss()
            }
        }
    }
}