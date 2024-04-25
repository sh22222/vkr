package com.example.vkr.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("select * from Genre")
    fun getGenre(): List<Genre>
    @Insert
    fun isertGenre(genre: Genre)
}