package com.amsavarthan.note.domain.note

interface NoteDataSource {

    suspend fun getAllNotes(): List<Note>
    suspend fun getNoteById(id: Long): Note?
    suspend fun insertNote(note: Note): Long
    suspend fun deleteNoteById(id: Long)

}