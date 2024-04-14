package com.example.vkr.mainScreen.Search

import java.io.Serializable
import java.util.Date

class Game (var idGame:Int,
            var imageId:Int,
            var name:String,
            var platform:String,
            var genre:String,
            var developer : String,
            var publisher : String,
            var description:String,
            var releaseDate : String):Serializable{
}