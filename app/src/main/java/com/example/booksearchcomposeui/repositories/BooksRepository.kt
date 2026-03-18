package com.example.booksearchcomposeui.repositories

import com.example.booksearchcomposeui.data.Book

interface BooksRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBook(id: String): Book?
    suspend fun searchBook(query: String): List<Book>
}
