package com.amsavarthan.note.android.screens.noteDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amsavarthan.note.domain.note.Note
import com.amsavarthan.note.domain.note.NoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val noteDataSource: NoteDataSource
) : ViewModel() {

    private var noteId = savedStateHandle.get<Long>("noteId")
    private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "Untitled")
    private val noteContent = savedStateHandle.getStateFlow("noteContent", "")

    init {
        noteId?.let { noteId ->
            if (noteId == -1L) {
                createNewNote()
                return@let
            }
            viewModelScope.launch {
                noteDataSource.getNoteById(noteId)?.let { note ->
                    savedStateHandle["noteTitle"] = note.title
                    savedStateHandle["noteContent"] = note.content
                }
            }
        }
    }

    var state = combine(noteTitle, noteContent) { title, content ->
        NoteDetailState(
            noteTitle = title,
            noteContent = content,
        )
    }.onEach(::updateNote).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        NoteDetailState()
    )

    private fun createNewNote() {
        viewModelScope.launch {
            noteId = noteDataSource.insertNote(Note())
            noteDataSource.getNoteById(noteId!!)?.let { note ->
                savedStateHandle["noteTitle"] = note.title
                savedStateHandle["noteContent"] = note.content
            }
        }
    }

    private fun updateNote(state: NoteDetailState) {
        viewModelScope.launch {
            noteDataSource.getNoteById(noteId!!)?.let { note ->
                noteDataSource.insertNote(
                    note.copy(
                        title = state.noteTitle, content = state.noteContent
                    )
                )
            }
        }
    }

    fun updateNoteTitle(title: String) {
        savedStateHandle["noteTitle"] = title
    }

    fun updateNoteContent(content: String) {
        savedStateHandle["noteContent"] = content
    }

    fun deleteNote() {
        viewModelScope.launch {
            noteDataSource.deleteNoteById(noteId!!)
        }
    }

}