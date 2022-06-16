package com.example.books.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.databinding.ItemPopularBinding
import com.example.books.model.Book

class AdapterPopularBooks(
    private var clickOnItem: (Book) -> Unit

): RecyclerView.Adapter<ViewHolderPopularBooks>() {

    private var listPopularBooks: List<Book> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPopularBooks {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPopularBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_popular, parent, false)
        return ViewHolderPopularBooks(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderPopularBooks, position: Int) {
        holder.bind(listPopularBooks[position], clickOnItem)
    }

    override fun getItemCount(): Int {
        return listPopularBooks.size
    }

    fun setList(list: List<Book>){
        listPopularBooks = list
    }
}

class ViewHolderPopularBooks(var binding: ItemPopularBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(book: Book, clickOnItem: (Book) -> Unit){
        binding.nameBookPopular.text = book.name
        binding.authorBookPopular.text = book.author

        binding.authorBookPopular.setOnClickListener{
            clickOnItem(book)
        }
        binding.cardPopular.setOnClickListener {
            clickOnItem(book)
        }
        binding.nameBookPopular.setOnClickListener {
            clickOnItem(book)
        }
        binding.authorBookPopular.setOnClickListener {
            clickOnItem(book)
        }
    }
}