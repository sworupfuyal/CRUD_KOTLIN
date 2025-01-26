package com.example.crud.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.crud.model.userModel
import com.example.crud.repository.UserRepository
import com.google.firebase.auth.FirebaseUser

class UserViewModel(val repo : UserRepository) {

    fun login(email: String, password: String, callback: (Boolean, String) -> Unit){
        repo.login(email,password,callback)
    }

    fun register(
        email: String, password: String,
        callback: (Boolean, String, String) -> Unit
    ){
        repo.register(email, password, callback)
    }

    fun forgetPassword(email: String, callback: (Boolean, String) -> Unit){
        repo.forgetPassword(email, callback)
    }

    fun addUserToDatabase(
        userId: String, userModel: userModel,
        callback: (Boolean, String) -> Unit
    ){
        repo.addUserToDatabase(userId,userModel,callback)
    }

    fun getCurrentUser(): FirebaseUser?{
        return repo.getCurrentUser()
    }


    var _userData = MutableLiveData<userModel?>()
    var userData = MutableLiveData<userModel?>()
        get() = _userData

    fun getUserFromDatabase(
        userId: String,
    ){
        repo.getUserFromDatabase(userId){
                user,success,message->
            if(success){
                _userData.value = user
            }
        }
    }


}