package com.example.vkr.mainScreen.Search

class Rating (login0:String, rating0:Double){
    private var login: String = login0
    private var rating: Double = rating0

    fun getLogin():String{
        return login
    }
    fun getRating():Double{
        return rating
    }
}