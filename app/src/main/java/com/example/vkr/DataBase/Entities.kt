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


