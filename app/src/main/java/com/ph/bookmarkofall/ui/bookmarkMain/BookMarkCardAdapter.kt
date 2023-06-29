package com.ph.bookmarkofall.ui.bookmarkMain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ph.bookmarkofall.data.model.BookmarkCard
import com.ph.bookmarkofall.databinding.ItemBookmarkCardBinding

class BookMarkCardAdapter :
    ListAdapter<BookmarkCard, BookMarkCardAdapter.BookMarkerCardViewHolder>(object : DiffUtil.ItemCallback<BookmarkCard>(){
        override fun areItemsTheSame(oldItem: BookmarkCard, newItem: BookmarkCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BookmarkCard, newItem: BookmarkCard): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkerCardViewHolder {
        val binding = ItemBookmarkCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BookMarkerCardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookMarkerCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookMarkerCardViewHolder(private val binding: ItemBookmarkCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookmarkCard) {
            binding.bookName.text = item.bookName
            binding.bookmarkValue.text = item.bookmarkValue
        }
    }
}