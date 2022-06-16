package com.example.books.repozitory

import androidx.lifecycle.LiveData
import com.example.book1.BookDAO
import com.example.books.model.Book

class BooksRepository (private val bookDAO: BookDAO) {

    suspend fun addBook(book: Book){
        bookDAO.addBook(book)
    }

    fun getAllBooks (): LiveData<List<Book>> {
        return bookDAO.getAllBooks()
    }

    fun getBookById (id: Int): LiveData<Book> {
        return bookDAO.getBookById(id)
    }


    fun getAllFavoriteBooks (): LiveData<List<Book>> {
        return bookDAO.getAllFavoriteBooks()
    }

    fun getAllPopularBooks (): LiveData<List<Book>> {
        return bookDAO.getAllPopularBooks()
    }

    suspend fun incrementPopulation(id: Int, score: Int){
        bookDAO.incrementPopulation(id, score)
    }

    suspend fun addOrDeleteInFavorite(id: Int, isFavorite: Boolean){
        bookDAO.addOrDeleteInFavorite(id, isFavorite)
    }
}