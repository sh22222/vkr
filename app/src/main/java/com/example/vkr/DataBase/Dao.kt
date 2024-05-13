package com.example.vkr.DataBase

import androidx.room.Dao
import androidx.room.Delete
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
    @Query("select * from Wishlist where idGame=:idGame and login=:login")
    fun checkGameForUserInWishlist(idGame: Int, login: String):Wishlist
    @Query("select nameDeveloper from Developer")
    fun getDeveloper():List<String>
    @Query("select namePublisher from Publisher")
    fun getPublisher():List<String>
    @Query("select * from News order by idNews desc")
    fun getNews():List<News>
    @Transaction
    @Query("select * from Games order by idGame desc")
    fun getAllDataGames(): List<AllDataGames>
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
    @Insert
    fun insertWishlist(wishlist: Wishlist)
    @Insert
    fun insertNews(news: News)
    @Delete
    fun deleteWishlist(wishlist: Wishlist)
    @Update
    fun updateProfile(profile: Profile)

}