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
import com.example.books.R
import com.example.books.databinding.FragmentViewBookBinding
import com.example.books.viewmodel.BooksViewModel

class ViewBookFragment : Fragment() {

    private lateinit var booksViewModel: BooksViewModel
    private lateinit var binding: FragmentViewBookBinding

    private var fav = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_book, container, false)

        booksViewModel = ViewModelProvider(this)[BooksViewModel::class.java]

        binding.nameBook.text = arguments?.getString("name")
        binding.authorBook.text = arguments?.getString("author")
        binding.textRating.text = "${arguments?.getFloat("rating")}/5.0"
        binding.ratingBarNew.rating = arguments?.getFloat("rating")!!
        binding.desc.text = arguments?.getString("description")
        binding.buyButton.text =  "Buy Now for $${arguments?.getString("price")}"




        booksViewModel.getBookById(arguments?.getInt("id")!!).observe(viewLifecycleOwner, Observer {
            if (it.is_favorite){
                binding.addToFavorite.setImageResource(R.drawable.ic_favorite_3)
            }else{
                binding.addToFavorite.setImageResource(R.drawable.ic_favorite_1)
            }
            fav = !it.is_favorite
        })



        binding.addToFavorite.setOnClickListener {
            booksViewModel.addOrDeleteInFavorite(arguments?.getInt("id")!!, fav)
            if (fav){
                Toast.makeText(context, "Add to favorites", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Remove from favorites", Toast.LENGTH_SHORT).show()
            }
        }




        binding.backButton.setOnClickListener {
            replaceFragment()
        }

        return binding.root
    }

    private fun replaceFragment(){
        val fragment = HomeFragment()
        val transition = requireActivity().supportFragmentManager.beginTransaction()
        transition.replace(R.id.fragmentContainer, fragment)
        transition.commit()
    }

}