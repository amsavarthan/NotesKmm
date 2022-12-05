package com.amsavarthan.note.android.screens.notesList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amsavarthan.note.android.R
import com.amsavarthan.note.android.screens.Screen
import com.amsavarthan.note.android.screens.components.NoteView
import com.amsavarthan.note.android.screens.components.SearchBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesListScreen(
    navController: NavController,
    viewModel: NotesListViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(true) {
        viewModel.getAllNotes()
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        floatingActionButton = {
            LargeFloatingActionButton(
                onClick = { navController.navigate(Screen.toNoteDetailDirection()) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        Box {
            LazyColumn(
                contentPadding = PaddingValues(top = 24.dp, bottom = 160.dp)
            ) {
                item {
                    SearchBar(
                        modifier = Modifier.statusBarsPadding(),
                        value = state.searchQuery,
                        onChange = viewModel::onSearch,
                        onClear = viewModel::onSearch
                    )
                }
                items(state.notes, key = { note -> note.id!! }) { note ->
                    NoteView(note = note) { id ->
                        navController.navigate(Screen.toNoteDetailDirection(id))
                    }
                }
            }
            if (state.notes.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        6.dp,
                        Alignment.CenterVertically
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    val title = if (state.searchQuery.isNotBlank())
                        stringResource(id = R.string.no_results_placeholder_title)
                    else
                        stringResource(id = R.string.placeholder_title)

                    val content = if (state.searchQuery.isNotBlank())
                        stringResource(id = R.string.no_results_placeholder_content)
                            .format(state.searchQuery)
                    else
                        stringResource(id = R.string.placeholder_content)

                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                    Text(
                        text = content,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
