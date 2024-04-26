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
    @Query("select * from Games")
    fun getGames(): List<Games>
    @Insert
    fun insertGenre(genre: Genre)
    @Insert
    fun insertGame(games: Games)


    @Transaction
    @Query("select * from Genre")
    fun getGenresWithGames():List<GamesForGenres>
    @Transaction
    @Query("select * from Platform")
    fun getPlatformWithGames():List<GamesForPlatform>
    @Transaction
    @Query("select * from Publisher")
    fun getPublishersWithGames():List<GamesForPublisher>

}