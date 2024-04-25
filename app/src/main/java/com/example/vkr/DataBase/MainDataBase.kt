package com.example.vkr.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [Genre::class, Platform::class], version = 1)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao():Dao
    companion object{
        fun getDataBase(context: Context):MainDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDataBase::class.java,
                "main.db").build()
        }
    }
}