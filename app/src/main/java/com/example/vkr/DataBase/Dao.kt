package com.example.vkr.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("select * from Genre")
    fun getGenre(): List<Genre>
    @Query("select * from Platform")
    fun getPlatform(): List<Platform>
    @Query("select * from Publisher")
    fun getPublisher(): List<Publisher>
    @Query("select * from Developer")
    fun getDeveloper(): List<Developer>
    @Query("select * from Games")
    fun getGames(): List<Games>
    @Query("select * from Profile where login=:login or email=:email")
    fun findProfileReg(login : String, email : String):List<Profile>
    @Query("select * from Profile where login=:login and password=:password")
    fun findProfile(login : String, password : String):List<Profile>

    @Insert
    fun insertGenre(genre: Genre)
    @Insert
    fun insertPlatform(platform: Platform)
    @Insert
    fun insertPublisher(publisher: Publisher)
    @Insert
    fun insertDeveloper(developer: Developer)
    @Insert
    fun insertGame(games: Games)
    @Insert
    fun insertGenresForGames(genresForGames: genresForGames)
    @Insert
    fun insertPlatformsForGames(platformsForGames: platformsForGames)
    @Insert
    fun insertPublishersForGames(publishersForGames: publishersForGames)
    @Insert
    fun insertProfile(profile: Profile)



//    @Transaction
//    @Query("select * from Games where idDeveloper=:idDeveloper")
//    fun getDeveloperWithGames(idDeveloper: Developer):List<DeveloperWithGames>
//    @Transaction
//    @Query("select * from Genre where idGenre=:idGenre")
//    fun getGenreWithGames(idGenre: Genre):List<GamesForGenres>
//    @Transaction
//    @Query("select * from Platform where idPlatform=:idPlatform")
//    fun getPlatformWithGames(idPlatform: Platform):List<GamesForPlatform>
//    @Transaction
//    @Query("select * from Publisher where idPublisher=:idPublisher")
//    fun getPublisherWithGames(idPublisher: Publisher):List<GamesForPublisher>

}