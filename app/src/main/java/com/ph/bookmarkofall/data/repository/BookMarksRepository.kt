package com.ph.bookmarkofall.data.repository

import com.ph.bookmarkofall.data.api.BookMarksApi
import com.ph.bookmarkofall.data.model.Book
import com.ph.bookmarkofall.data.model.BookmarkCard
import javax.inject.Inject

class BookMarksRepository @Inject constructor(private val bookMarksApi: BookMarksApi ){

    suspend fun getBookMarks():List<BookmarkCard>{
        return bookMarksApi.getBookMarks()
    }

    suspend fun getTop5Book() : List<Book>{
        return bookMarksApi.getTop5BookImage()
    }
}