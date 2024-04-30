package com.example.vkr.mainScreen.Profile

import java.io.Serializable

class Profile(
    private var login : String,
    private var email : String,
    private var password : String,
    private var pathPict: String
) : Serializable {
    fun getLogin():String {
        return login
    }
    fun getEmail():String{
        return email
    }
}