package com.example.crud.viewmodel

import com.example.crud.model.userModel
import com.example.crud.repository.UserRepository

class UserViewModel(val repo:UserRepository) {


    fun login(email:String,password:String,callback:(Boolean,String) ->Unit){
        repo.login(email,password,callback)
    }

    fun register(email:String,password:String,callback:(Boolean,String,String) ->Unit){
        repo.register(email,password,callback)
    }



    fun forgetPassword(email:String,callback:(Boolean,String) ->Unit){
        repo.forgetPassword(email,callback)

    }


    fun addUserToDatabase(userId:String, userModel: userModel, callback:(Boolean, String) ->Unit) {
        repo.addUserToDatabase(userId, userModel, callback)
    }
}