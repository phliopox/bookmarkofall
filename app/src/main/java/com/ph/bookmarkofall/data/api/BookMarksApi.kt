package com.ph.bookmarkofall.data.api

import com.ph.bookmarkofall.data.model.Book
import com.ph.bookmarkofall.data.model.BookmarkCard
import retrofit2.http.GET

interface BookMarksApi {

    @GET("bookmarks.json")
    suspend fun getBookMarks() : List<BookmarkCard>

    @GET("top5Book.json")
    suspend fun getTop5BookImage() : List<Book>
}