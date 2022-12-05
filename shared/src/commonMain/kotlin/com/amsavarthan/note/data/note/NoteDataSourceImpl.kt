package com.amsavarthan.note.data.note

import com.amsavarthan.note.domain.note.Note
import com.amsavarthan.note.domain.note.NoteDataSource
import com.amsavarthan.note.domain.time.DateTimeUtil
import database.NoteQueries

class NoteDataSourceImpl(
    private val queries: NoteQueries
) : NoteDataSource {
    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes().executeAsList().map { it.asNote() }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries.getNoteById(id).executeAsOneOrNull()?.asNote()
    }

    override suspend fun insertNote(note: Note): Long {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.content,
            created = DateTimeUtil.toEpochMillis(note.created)
        )
        return queries.getInsertedNoteId().executeAsOne().MAX!!
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}