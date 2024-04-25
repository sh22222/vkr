package com.example.vkr.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey(autoGenerate = true) var id : Int? = 1,
    @ColumnInfo(name = "name") var name : String
)
@Entity
data class Platform(
    @PrimaryKey(autoGenerate = true) var id : Int? = 1000,
    @ColumnInfo(name = "name") var namePlatform : String
)