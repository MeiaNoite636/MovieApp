package com.example.themovie.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.themovie.util.Columns
import com.example.themovie.util.Tables

@Entity(tableName = Tables.MOVIE_TABLE)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,

    @ColumnInfo(name = Columns.MOVIE_TITLE_COLUMN)
    val title: String?,

    @ColumnInfo(name = Columns.MOVIE_POSTER_COLUMN)
    val poster: String?,

    @ColumnInfo(name = Columns.MOVIE_RUNTIME_COLUMN)
    val runtime: Int?,

    @ColumnInfo(name = Columns.MOVIE_INSERTION_COLUMN   )
    val insertion: Long?
)
