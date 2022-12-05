package com.amsavarthan.note.android.screens.notesList

import com.amsavarthan.note.domain.note.Note

data class NotesListState(
    val notes: List<Note> = emptyList(),
    val searchQuery: String = "",
    val newNoteId: Long = 0,
)