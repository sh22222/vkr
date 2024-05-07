package com.example.vkr.mainScreen.Search

import java.io.Serializable
import java.util.Date

class Game (var idGame:Int,
            var name:String,
            var platform:List<String>,
            var genre:List<String>,
            var developer : String,
            var publisher : List<String>,
            var description:String,
            var releaseDate : String,
            var pathImage:String):Serializable