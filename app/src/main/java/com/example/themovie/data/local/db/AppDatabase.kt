package com.example.themovie.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.themovie.data.local.dao.MovieDao
import com.example.themovie.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun movieDao(): MovieDao
}