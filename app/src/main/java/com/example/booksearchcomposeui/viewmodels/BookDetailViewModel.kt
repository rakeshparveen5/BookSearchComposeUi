package com.example.booksearchcomposeui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearchcomposeui.data.Book
import com.example.booksearchcomposeui.repositories.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val booksRepository: BooksRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var uiState by mutableStateOf(BookDetailUiState())
        private set

    init {
        val id = savedStateHandle.get<String>("bookId")!!
        loadBook(id)
    }

    fun loadBook(id: String) {
        uiState = uiState.copy(isLoading = true)
        viewModelScope.launch {
            val book = booksRepository.getBook(id)
            uiState = uiState.copy(book = book, isLoading = false)
        }
    }
}

data class BookDetailUiState(
    val isLoading: Boolean = false,
    val book: Book? = null
)