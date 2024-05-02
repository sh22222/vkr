package com.example.vkr.DataBase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.io.Serializable
import java.util.Date

@Entity
data class Genre(
    @PrimaryKey var idGenre : Int,
    @ColumnInfo(name = "name") var name : String
)
@Entity
data class Platform(
    @PrimaryKey var idPlatform : Int,
    @ColumnInfo(name = "name") var namePlatform : String
)
@Entity
data class Publisher(
    @PrimaryKey var idPublisher : Int,
    var namePublisher : String
)
@Entity
data class Developer(
    @PrimaryKey var idDeveloper: Int,
    var nameDeveloper : String
)
@Entity
data class Games(
    @PrimaryKey var idGame : Int,
    var nameGame : String,
    var description : String,
    var dataRelease : String,
    var pathPict : String,
    var idDeveloper : Int
)
@Entity
data class Profile(
    @PrimaryKey var login : String,
    var email : String,
    var password : String,
    var pathPict: String
):Serializable
@Entity
data class News(
    @PrimaryKey var idNews : Int,
    var headline : String,
    var description: String,
    var datePublish : String
)
//for genre many-to-many
@Entity (primaryKeys = ["idGame", "idGenre"])
data class genresForGames(
    val idGame: Int,
    val idGenre:Int
)
data class GamesForGenres(
    @Embedded val genre: Genre,
    @Relation(
        parentColumn = "idGenre",
        entityColumn = "idGame",
        associateBy = Junction(genresForGames::class)
    )
    val listGames: List<Games>
)
data class GenresForGame(
    @Embedded val games: Games,
    @Relation(
        parentColumn = "idGame",
        entityColumn = "idGenre",
        associateBy = Junction(genresForGames::class)
    )
    val listGenres: List<Genre>
)
//for platform many-to-many
@Entity(primaryKeys = ["idGame", "idPlatform"])
data class platformsForGames(
    val idGame : Int,
    val idPlatform: Int
)
data class GamesForPlatform(
    @Embedded val platform: Platform,
    @Relation(
        parentColumn = "idPlatform",
        entityColumn = "idGame",
        associateBy = Junction(platformsForGames::class)
    )
    val listGames:List<Games>
)
data class PlatformsForGame(
    @Embedded val games: Games,
    @Relation(
        parentColumn = "idGame",
        entityColumn = "idPlatform",
        associateBy = Junction(platformsForGames::class)
    )
    val listPlatforms:List<Platform>
)
//for publisher many-to-many
@Entity(primaryKeys = ["idPublisher", "idGame"])
data class publishersForGames(
    val idPublisher : Int,
    val idGame : Int
)
data class GamesForPublisher(
    @Embedded val publisher: Publisher,
    @Relation(
        parentColumn = "idPublisher",
        entityColumn = "idGame",
        associateBy = Junction(publishersForGames::class)
    )
    val listGames: List<Games>
)
data class PublishersForGame(
    @Embedded val games: Games,
    @Relation(
        parentColumn = "idGame",
        entityColumn = "idPublisher",
        associateBy = Junction(publishersForGames::class)
    )
    val listPublishers: List<Publisher>
)
//for profile-games many-to-many
@Entity(primaryKeys = ["idGame", "login"])
data class Wishlist(
    var idGame : Int,
    var login: String
)
data class gamesForProfile(
    @Embedded val profile: Profile,
    @Relation(
        parentColumn = "idProfile",
        entityColumn = "idGame",
        associateBy = Junction(Wishlist::class)
    )
    val listGames: List<Games>
)
//for developer-game one-to-many
data class DeveloperWithGames(
    @Embedded val developer: Developer,
    @Relation(
        parentColumn = "idDeveloper",
        entityColumn = "idDeveloper"
    )
    val listGames: List<Games>
)

data class AllDataGames(
    @Embedded val games: Games,
    @Relation(
        parentColumn = "idGame",
        entityColumn = "idGenre",
        associateBy = Junction(genresForGames::class)
    )
    val listGenres: List<Genre>,
    @Relation(
        parentColumn = "idGame",
        entityColumn = "idPlatform",
        associateBy = Junction(platformsForGames::class)
    )
    val listPlatforms:List<Platform>,
    @Relation(
        parentColumn = "idGame",
        entityColumn = "idPublisher",
        associateBy = Junction(publishersForGames::class)
    )
    val listPublishers: List<Publisher>,

    @Relation(
        parentColumn = "idDeveloper",
        entityColumn = "idDeveloper"
    )
    val developer: Developer
){
    fun getGenresName():ArrayList<String>{
        var namesGenres = ArrayList<String>()
        for(i in 0..listGenres.size-1){
            namesGenres.add(listGenres[i].name)
        }
        return namesGenres
    }
    fun getPlatformsName():ArrayList<String>{
        var namesDeveloper = ArrayList<String>()
        for(i in 0..listPlatforms.size-1){
            namesDeveloper.add(listPlatforms[i].namePlatform)
        }
        return namesDeveloper
    }
    fun getPublishersName():ArrayList<String>{
        var namesPublishers = ArrayList<String>()
        for(i in 0..listPublishers.size-1){
            namesPublishers.add(listPublishers[i].namePublisher)
        }
        return namesPublishers
    }
}



