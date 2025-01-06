package com.example.crud.repository

import android.widget.Toast
import com.example.crud.model.userModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImpl :UserRepository {
    var auth: FirebaseAuth=FirebaseAuth.getInstance()
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var reference= database.reference.child("users")


    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
       auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
           if(it.isSuccessful){
               callback(false,it.exception?.message.toString())
           }
       }
    }

    override fun register(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
       auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
           if(it.isSuccessful){
               callback(true,"Registration success",
                   auth.currentUser?.uid.toString())

           }else{
               callback(false,it.exception?.message.toString(),"")
           }
       }
    }

    override fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Password reset link $email")
            }else{
                callback(false,it.exception?.message.toString())
            }
        }    }

    override fun addUserToDatabase(
        userId: String,
        userModel: userModel,
        callback: (Boolean, String) -> Unit
    ) {
        reference.child(userId.toString()).setValue(userModel).addOnCompleteListener {

            if(it.isSuccessful){
                callback(true,"Registration successful")

            } else{
               callback(false,it.exception?.message.toString())

            }
        }
    }
}