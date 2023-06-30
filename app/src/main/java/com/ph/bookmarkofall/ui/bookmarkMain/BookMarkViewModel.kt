package com.ph.bookmarkofall.ui.bookmarkMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ph.bookmarkofall.data.model.BookmarkCard
import com.ph.bookmarkofall.data.repository.BookMarksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(private val bookMarksRepository: BookMarksRepository) : ViewModel(){

    private val _bookMarkers : MutableLiveData<List<BookmarkCard>> = MutableLiveData()
    val bookMarkers : LiveData<List<BookmarkCard>> = _bookMarkers

    suspend fun getBookMarkers(){
        viewModelScope.launch {
            _bookMarkers.value = bookMarksRepository.getBookMarks()
        }
    }
}