package com.ph.bookmarkofall.data.model

// 작가도 추가할것
data class Book(
    val id : Int,
    val bookName : String,
    val imageUrl : String,
    val volume : Int,
    val bookMarkCount : Int
)
