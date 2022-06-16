package com.example.books.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books.R
import com.example.books.adapters.AdapterNewBooks
import com.example.books.adapters.AdapterPopularBooks
import com.example.books.databinding.FragmentHomeBinding
import com.example.books.model.Book
import com.example.books.viewmodel.BooksViewModel

class HomeFragment : Fragment() {

    private lateinit var booksViewModel: BooksViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterNewBooks: AdapterNewBooks
    private lateinit var adapterPopularBooks: AdapterPopularBooks

    private lateinit var bundle: Bundle


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        bundle = Bundle()

        setNameUser()

        binding.recPopular.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recNew.layoutManager = LinearLayoutManager(context)


//        popular books
        adapterPopularBooks = AdapterPopularBooks { selectedFavoriteButton: Book ->
            clickOnItem(
                selectedFavoriteButton
            )
        }
        binding.recPopular.adapter = adapterPopularBooks

        booksViewModel.getAllPopularBooks.observe(viewLifecycleOwner, Observer {
            adapterPopularBooks.setList(it.reversed())
            adapterPopularBooks.notifyDataSetChanged()
        })



//        new books
        adapterNewBooks = AdapterNewBooks(
            {selectedFavoriteButton: Book -> clickOnFavoriteIcon(selectedFavoriteButton)},
            {selectedFavoriteButton: Book -> clickOnItem(selectedFavoriteButton)}
        )
        binding.recNew.adapter = adapterNewBooks

        booksViewModel.getAllBooks.observe(viewLifecycleOwner, Observer {
            adapterNewBooks.setList(it.reversed())
            adapterNewBooks.notifyDataSetChanged()
        })






        return binding.root
    }

    private fun setNameUser(){
        binding.nameUser.text = "Hi, ${getNameInSharedPreference()}"
    }

    private fun getNameInSharedPreference() = requireActivity().getSharedPreferences("NAME", Context.MODE_PRIVATE).getString("name", "User")

    private fun clickOnFavoriteIcon(book: Book){
        booksViewModel.addOrDeleteInFavorite(book.id, !book.is_favorite)
        if(book.is_favorite)
            Toast.makeText(context, "Remove from favorites", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Add to favorites", Toast.LENGTH_SHORT).show()
    }

    private fun clickOnItem(book: Book){
        booksViewModel.incrementPopulation(book.id, book.popular_score + 1)

        bundle.putInt("id", book.id)
        bundle.putString("name", book.name)
        bundle.putString("author", book.author)
        bundle.putFloat("rating", book.rating)
        bundle.putString("description", book.description)
        bundle.putString("price", book.price)
        bundle.putBoolean("is_favorite", book.is_favorite)

        replaceFragment()
    }


    private fun replaceFragment(){
        val fragment = ViewBookFragment()
        fragment.arguments = bundle

        val transition = requireActivity().supportFragmentManager.beginTransaction()
        transition.replace(R.id.fragmentContainer, fragment)
        transition.commit()
    }



}