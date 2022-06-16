package com.example.books.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_data_table")
data class Book (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "author")
    var author : String,

    @ColumnInfo(name = "description")
    var description : String,

    @ColumnInfo(name = "price")
    var price : String,

    @ColumnInfo(name = "image_name")
    var image_name : String,

    @ColumnInfo(name = "popular_score")
    var popular_score : Int,

    @ColumnInfo(name = "rating")
    var rating : Float,

    @ColumnInfo(name = "is_favorite")
    var is_favorite : Boolean = false
)