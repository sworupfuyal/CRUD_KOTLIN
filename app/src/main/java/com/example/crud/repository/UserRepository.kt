package com.example.crud.repository

import com.example.crud.model.userModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {

//    {
//    success: true
//    message: Login success
//    }

    fun login(
        email: String, password: String,
        callback: (Boolean, String) -> Unit
    )

    fun register(
        email: String, password: String,
        callback: (Boolean, String, String) -> Unit
    )

    fun forgetPassword(email: String, callback: (Boolean, String) -> Unit)

    fun addUserToDatabase(
        userId: String, userModel: userModel,
        callback: (Boolean, String) -> Unit
    )


    fun logout(callback: (Boolean, String) -> Unit)

    fun editProfile(
        userId: String, data: MutableMap<String, Any>,
        callback: (Boolean, String) -> Unit
    )

    fun getCurrentUser(): FirebaseUser?

    fun getUserFromDatabase(
        userId: String,
        callback: (userModel?, Boolean, String) -> Unit
    )


}