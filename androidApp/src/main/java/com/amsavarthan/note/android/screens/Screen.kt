package com.amsavarthan.note.android.screens

object Screen {
    const val NotesList = "notes_list"

    private const val NoteDetailDirection = "note_detail"
    const val NoteDetail = "${NoteDetailDirection}/{noteId}"

    fun toNoteDetailDirection(noteId: Long = -1): String {
        return "${NoteDetailDirection}/${noteId}"
    }

}
