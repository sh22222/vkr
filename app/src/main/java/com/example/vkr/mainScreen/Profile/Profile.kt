package com.example.vkr.mainScreen.Profile

import java.io.Serializable

class Profile(
    private var login : String,
    private var email : String,
    private var pathPict: String
) : Serializable {
    fun setLogin(setLogin:String){
        login = setLogin
    }
    fun setEmail(setEmail:String){
        email = setEmail
    }
    fun getLogin():String {
        return login
    }
    fun getEmail():String{
        return email
    }
    fun getPathPict():String{
        return pathPict
    }
}