package com.ph.bookmarkofall.ui.bookmarkMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ph.bookmarkofall.data.model.Book
import com.ph.bookmarkofall.data.model.BookmarkCard
import com.ph.bookmarkofall.data.repository.BookMarksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(private val bookMarksRepository: BookMarksRepository) :
    ViewModel() {

    private val _bookMarks: MutableLiveData<List<BookmarkCard>> = MutableLiveData()
    val bookMarks: LiveData<List<BookmarkCard>> = _bookMarks

    private val _top5book: MutableLiveData<List<Book>> = MutableLiveData()
    val top5book: LiveData<List<Book>> = _top5book

    init {
        //getBookMarkers()
        getTop5Book()
    }

    private fun getBookMarkers() {
        viewModelScope.launch {
            _bookMarks.value = bookMarksRepository.getBookMarks()
        }
    }

    private fun getTop5Book() {
        viewModelScope.launch {
            _top5book.value = bookMarksRepository.getTop5Book()
        }
    }
}