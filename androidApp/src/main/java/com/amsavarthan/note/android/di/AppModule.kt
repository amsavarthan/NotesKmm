package com.amsavarthan.note.android.di

import android.content.Context
import com.amsavarthan.note.data.local.DatabaseDriverFactory
import com.amsavarthan.note.data.note.NoteDataSourceImpl
import com.amsavarthan.note.database.NoteDatabase
import com.amsavarthan.note.domain.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesSqlDriver(@ApplicationContext context: Context): SqlDriver {
        return DatabaseDriverFactory(context).createDriver()
    }

    @Singleton
    @Provides
    fun providesNotesDatabase(sqlDriver: SqlDriver): NoteDatabase {
        return NoteDatabase(sqlDriver)
    }

    @Singleton
    @Provides
    fun providesNotesDataSource(database: NoteDatabase): NoteDataSource {
        return NoteDataSourceImpl(database.noteQueries)
    }

}