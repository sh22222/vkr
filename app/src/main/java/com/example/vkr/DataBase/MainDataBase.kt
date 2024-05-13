package com.example.vkr.DataBase
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database (entities = [
    Genre::class,
    Platform::class,
    Publisher::class,
    Developer::class,
    Games::class,
    genresForGames::class,
    platformsForGames::class,
    publishersForGames::class,
    Wishlist::class,
    Profile::class,
    News::class],
    version = 1)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getDao():Dao
    public
    companion object{
        fun getDataBase(context: Context):MainDataBase{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDataBase::class.java,
                "main.db").fallbackToDestructiveMigration().allowMainThreadQueries().build()
        }
    }
}