package com.amsavarthan.note.di

import com.amsavarthan.note.data.local.DatabaseDriverFactory
import com.amsavarthan.note.data.note.NoteDataSourceImpl
import com.amsavarthan.note.database.NoteDatabase
import com.amsavarthan.note.domain.note.NoteDataSource

class DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy {
        NoteDataSourceImpl(NoteDatabase(factory.createDriver()).noteQueries)
    }
}