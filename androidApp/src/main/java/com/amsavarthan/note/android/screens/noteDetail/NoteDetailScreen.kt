package com.amsavarthan.note.android.screens.noteDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.amsavarthan.note.android.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    navController: NavController,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val focusManager = LocalFocusManager.current
    var openDialog by remember { mutableStateOf(false) }

    if (openDialog) {
        AlertDialog(
            title = {
                Text("Delete note")
            },
            text = {
                Text("Are you sure do you want to delete this note?")
            },
            confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                    viewModel.deleteNote()
                    navController.navigateUp()
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog = false
                }) {
                    Text("Cancel")
                }
            },
            onDismissRequest = {
                openDialog = false
            },
        )
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    IconButton(onClick = {
                        openDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = navController::navigateUp) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BasicTextField(
                value = state.noteTitle,
                onValueChange = viewModel::updateNoteTitle,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    MaterialTheme.colorScheme.onSurface
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus(true)
                }),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (state.noteTitle.isBlank()) {
                            Text(
                                text = stringResource(id = R.string.title_hint),
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                            )
                        }
                        innerTextField()
                    }
                }
            )
            BasicTextField(
                value = state.noteContent,
                onValueChange = viewModel::updateNoteContent,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                ),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        if (state.noteContent.isBlank()) {
                            Text(
                                text = stringResource(id = R.string.content_hint),
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }

}