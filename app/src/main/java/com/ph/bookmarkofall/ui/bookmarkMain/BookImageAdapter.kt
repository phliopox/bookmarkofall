package com.ph.bookmarkofall.ui.bookmarkMain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ph.bookmarkofall.data.model.Book
import com.ph.bookmarkofall.databinding.ItemBookBinding

class BookImageAdapter : ListAdapter<Book, BookImageAdapter.BookImageViewHolder>(object : DiffUtil.ItemCallback<Book>(){
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }

}) {

    inner class BookImageViewHolder(private val binding : ItemBookBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(book : Book){
            binding.book = book
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookImageViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return BookImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}