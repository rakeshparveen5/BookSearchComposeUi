package com.example.booksearchcomposeui.di

import com.example.booksearchcomposeui.repositories.BooksRepository
import com.example.booksearchcomposeui.repositories.BooksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BookModule {
    @Binds
    fun provideBookRepository(impl: BooksRepositoryImpl): BooksRepository
}