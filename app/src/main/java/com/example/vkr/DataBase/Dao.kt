package com.example.vkr.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("select name from Genre")
    fun getGenre(): List<String>
    @Query("select name from Platform")
    fun getPlatform(): List<String>
    @Query("select * from Games order by idGame desc")
    fun getGames(): List<Games>
    @Query("select * from Profile where login=:login or email=:email")
    fun findProfileReg(login : String, email : String):List<Profile>
    @Query("select * from Profile where login=:login and password=:password")
    fun findProfile(login : String, password : String):List<Profile>
    @Query("update Profile set login=:newLogin, email=:email where login=:oldLogin")
    fun updateProfile(oldLogin:String, newLogin: String, email: String)
    @Query("update Profile set login=:newLogin, email=:email, password=:password where login=:oldLogin")
    fun updateProfileWithPass(oldLogin:String, newLogin: String, email: String, password: String)


    @Transaction
    @Query("select * from Games order by idGame desc")
    fun getAllDataGames(): List<AllDataGames>

//    @Transaction
//    @Query("select * from Games " +
//            "left join platformsForGames on Games.idGame=platformsForGames.idGame order by Games.idGame desc")
//    fun getPlatformDataGames(): List<AllDataGames>
    @RawQuery
    fun getDataGames(query: SupportSQLiteQuery): List<AllDataGames>

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


    @Update
    fun updateProfile(profile: Profile)

//запросы для поиска по играм


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