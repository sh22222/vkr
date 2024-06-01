package com.example.vkr.mainScreen.Profile

import java.io.Serializable

class Profile(
    private var login : String,
    private var email : String,
    private var id : String,
    var listGames: ArrayList<Long>
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
    fun getId():String{
        return id
    }
    fun addGame(game: Long){
        listGames.add(game)
    }
    fun deleteGame(game:Long){
        for(i in 0..listGames.size-1){
            if (game.compareTo(listGames[i])==0){
                listGames.removeAt(i)
                break
            }
        }
    }
    fun getList():ArrayList<Long>{
        return listGames
    }
}