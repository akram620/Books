package com.example.books.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.book1.BookDatabase
import com.example.books.repozitory.BooksRepository
import com.example.books.model.Book
import kotlinx.coroutines.launch

class BooksViewModel (application: Application): AndroidViewModel(application) {


    private var repository: BooksRepository

    init {
        val bookDAO = BookDatabase.getDatabase(application).bookDAO()
        repository = BooksRepository(bookDAO)
    }

    var getAllBooks = repository.getAllBooks()

    var getAllPopularBooks = repository.getAllPopularBooks()

    var getAllFavoriteBooks = repository.getAllFavoriteBooks()

    fun getBookById(id: Int): LiveData<Book>{
        return repository.getBookById(id)
    }


    fun addBook(book: Book) = viewModelScope.launch {
        repository.addBook(book)
    }

    fun incrementPopulation(id: Int, score: Int) = viewModelScope.launch {
        repository.incrementPopulation(id, score)
    }

    fun addOrDeleteInFavorite(id: Int, isFavorite: Boolean) = viewModelScope.launch {
        repository.addOrDeleteInFavorite(id, isFavorite)
    }

}