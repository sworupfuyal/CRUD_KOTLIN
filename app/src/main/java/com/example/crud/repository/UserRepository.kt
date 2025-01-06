package com.example.crud.repository

import com.example.crud.model.userModel

interface UserRepository {

    fun login(email:String,password:String,callback:(Boolean,String) ->Unit)

    fun register(email:String,password:String,callback:(Boolean,String,String) ->Unit)

    fun forgetPassword(email:String,callback:(Boolean,String) ->Unit)


    fun addUserToDatabase(userId:String,userModel: userModel,callback:(Boolean,String) ->Unit)
}