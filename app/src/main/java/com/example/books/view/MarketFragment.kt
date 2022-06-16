package com.example.books.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.books.R
import com.example.books.adapters.AdapterNewBooks
import com.example.books.databinding.FragmentHomeBinding
import com.example.books.databinding.FragmentMarketBinding
import com.example.books.model.Book
import com.example.books.viewmodel.BooksViewModel

class MarketFragment : Fragment() {

    private lateinit var booksViewModel: BooksViewModel
    private lateinit var binding: FragmentMarketBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_market, container, false)

        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        binding.addNewBookButton.setOnClickListener {

            val name = binding.nameAdd.text.toString()
            val author = binding.authorAdd.text.toString()
            val desc = binding.descAdd.text.toString()
            val price = binding.priceAdd.text.toString()
            val rating = binding.ratingAdd.text.toString()

            if (name.isEmpty() || author.isEmpty() || desc.isEmpty() || price.isEmpty()
                || rating.isEmpty() || !rating.toString().all { char -> char.isDigit() }){

                Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show()
            } else{
                booksViewModel.addBook(Book(0, name, author, desc, price, "",
                    0, rating.toFloat(), false))

                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            }


        }


        return binding.root
    }

}