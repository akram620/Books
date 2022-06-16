package com.example.book1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.books.model.Book

@Dao
interface BookDAO {

    @Insert
    suspend fun addBook(book: Book)

    @Query("SELECT * FROM books_data_table")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books_data_table WHERE id = :id")
    fun getBookById(id: Int): LiveData<Book>


    @Query("SELECT * FROM books_data_table WHERE popular_score > 2")
    fun getAllPopularBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books_data_table WHERE is_favorite = 1")
    fun getAllFavoriteBooks(): LiveData<List<Book>>

    @Query("UPDATE books_data_table SET popular_score = :score WHERE id = :id")
    suspend fun incrementPopulation(id: Int, score: Int)

    @Query("UPDATE books_data_table SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun addOrDeleteInFavorite(id: Int, isFavorite: Boolean)
}