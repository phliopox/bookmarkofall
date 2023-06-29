package com.ph.bookmarkofall.data.model

data class BookmarkCard (
    val id : String,
    val userId : String,
    val bookmarkValue : String,
    val bookName : String,
    val likeCounts : Int
        )