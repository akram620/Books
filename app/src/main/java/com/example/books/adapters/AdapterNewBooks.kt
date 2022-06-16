package com.example.books.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.databinding.ItemNewBinding
import com.example.books.model.Book

class AdapterNewBooks(
    private var clickOnFavoriteIcon: (Book) -> Unit,
    private var clickOnItem: (Book) -> Unit


): RecyclerView.Adapter<ViewHolderNewBooks>() {
    private var listNewBook: List<Book> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderNewBooks {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_new, parent, false)
        return ViewHolderNewBooks(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderNewBooks, position: Int) {
        holder.bind(
            listNewBook[position],
            clickOnFavoriteIcon,
            clickOnItem
        )
    }

    override fun getItemCount(): Int {
        return listNewBook.size
    }

    fun setList(list: List<Book>){
        listNewBook = list
    }
}

class ViewHolderNewBooks(var binding: ItemNewBinding): RecyclerView.ViewHolder(binding.root){

    fun bind(
        book: Book,
        clickOnFavoriteIcon: (Book) -> Unit,
        clickOnItem: (Book) -> Unit
    ) {
        binding.nameBookNew.text = book.name
        binding.authorBookNew.text = book.author
        binding.countView.text = book.popular_score.toString()
        binding.ratingBarNew.rating = book.rating

        if (book.is_favorite){
            binding.favoriteButtonNew.setImageResource(R.drawable.ic_favorite_3)
        }else{
            binding.favoriteButtonNew.setImageResource(R.drawable.ic_favorite_1)
        }

        binding.favoriteButtonNew.setOnClickListener {
            clickOnFavoriteIcon(book)
        }

        binding.layoutNewBook.setOnClickListener {
            clickOnItem(book)
        }
    }
}