package com.amsavarthan.note.android.screens.notesList

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amsavarthan.note.domain.note.Note
import com.amsavarthan.note.domain.note.NoteDataSource
import com.amsavarthan.note.domain.note.SearchNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val notesDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val searchNotes = SearchNotes()

    private var notes = savedStateHandle.getStateFlow("notes", emptyList<Note>())
    private var searchQuery = savedStateHandle.getStateFlow("searchQuery", "")

    val state = combine(notes, searchQuery) { notes, query ->
        NotesListState(
            notes = searchNotes.execute(notes, query),
            searchQuery = query,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        NotesListState()
    )

    fun getAllNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = notesDataSource.getAllNotes()
        }
    }

    fun onSearch(query: String = "") {
        savedStateHandle["searchQuery"] = query
    }

}