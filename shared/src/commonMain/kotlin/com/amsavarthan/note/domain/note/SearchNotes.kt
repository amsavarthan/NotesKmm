package com.amsavarthan.note.domain.note

import com.amsavarthan.note.domain.time.DateTimeUtil

class SearchNotes {

    fun execute(notes: List<Note>, query: String): List<Note> {
        if (query.isBlank()) return notes
        return notes.filter { note ->
            note.title.trim().lowercase().contains(query.lowercase()) ||
                    note.content.trim().lowercase().contains(query.lowercase())
        }.sortedByDescending { note ->
            DateTimeUtil.toEpochMillis(note.created)
        }
    }

}