package com.example.books.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.books.R
import com.example.books.adapters.AdapterNewBooks
import com.example.books.databinding.FragmentFavoriteBinding
import com.example.books.model.Book
import com.example.books.viewmodel.BooksViewModel

class FavoriteFragment : Fragment() {

    private lateinit var booksViewModel: BooksViewModel
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var adapterNewBooks: AdapterNewBooks

    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)

        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        binding.recFavItem.layoutManager = LinearLayoutManager(context)

        bundle = Bundle()

        adapterNewBooks = AdapterNewBooks(
            {selectedFavoriteButton: Book -> clickOnFavoriteIcon(selectedFavoriteButton)},
            {selectedFavoriteButton: Book -> clickOnItem(selectedFavoriteButton)}
        )
        binding.recFavItem.adapter = adapterNewBooks

        booksViewModel.getAllFavoriteBooks.observe(viewLifecycleOwner, Observer {
            adapterNewBooks.setList(it.reversed())
            adapterNewBooks.notifyDataSetChanged()
        })






        return binding.root
    }

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