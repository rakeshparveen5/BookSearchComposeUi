package com.example.booksearchcomposeui.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.booksearchcomposeui.data.Book
import com.example.booksearchcomposeui.ui.BookItem
import com.example.booksearchcomposeui.viewmodels.BookSearchUiState
import com.example.booksearchcomposeui.viewmodels.BookSearchViewModel

@Composable
fun BookSearchScreen(
    navController: NavHostController,
    viewModel: BookSearchViewModel = hiltViewModel()
) {
    val state = viewModel.uiState
    val onValueChange = { v: String -> viewModel.onSearchQueryChanged(query = v) }
    val onBookClick = { book: Book -> }

    BookSearchContent(state = state, onValueChange = onValueChange, onBookClicked = onBookClick)
}

@Composable
fun BookSearchContent(
    state: BookSearchUiState,
    onValueChange: (String) -> Unit,
    onBookClicked: (Book) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = state.searchQuery,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            placeholder = { Text(text = "Search books") },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn {
                items(items = state.books, key = { book: Book -> book.id }) {
                    BookItem(book = it, onClick = { onBookClicked(it) })
                }
            }
        }
    }
}

@Preview
@Composable
fun BookSearchContentPreview() {
    val books = (0..10).toList().map {
        Book(
            id = "id $it",
            name = "name $it is very long so it should be truncated",
            author = "author $it",
            imageUrl = "imageUrl $it",
            description = "description $it",
            rating = it.toFloat(),
            reviewCount = it
        )
    }
    val state = BookSearchUiState(isLoading = false, books = books, searchQuery = "")
    BookSearchContent(state = state, onValueChange = { _ -> }, onBookClicked = {})
}