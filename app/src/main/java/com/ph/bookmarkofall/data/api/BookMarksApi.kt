package com.ph.bookmarkofall.data.api

import com.ph.bookmarkofall.data.model.BookmarkCard
import retrofit2.http.GET

interface BookMarksApi {

    @GET("bookmarks.json")
    suspend fun getBookMarks() : List<BookmarkCard>
}