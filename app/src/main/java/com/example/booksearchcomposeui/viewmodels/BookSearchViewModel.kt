package com.example.booksearchcomposeui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearchcomposeui.data.Book
import com.example.booksearchcomposeui.repositories.BooksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel @Inject constructor(
    private val booksRepository: BooksRepository
) : ViewModel() {
    var uiState by mutableStateOf(BookSearchUiState())
        private set

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val books = booksRepository.getBooks()
            uiState = uiState.copy(books = books, isLoading = false)
        }
    }

    fun onSearchQueryChanged(query: String) {
        uiState = uiState.copy(searchQuery = query, isLoading = true)
        viewModelScope.launch {
            val books = booksRepository.searchBook(query)
            uiState = uiState.copy(books = books, isLoading = false)
        }
    }
}

data class BookSearchUiState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList()
)