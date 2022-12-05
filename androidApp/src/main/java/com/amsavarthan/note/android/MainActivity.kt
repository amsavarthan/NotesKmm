package com.amsavarthan.note.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amsavarthan.note.android.screens.Screen
import com.amsavarthan.note.android.screens.noteDetail.NoteDetailScreen
import com.amsavarthan.note.android.screens.notesList.NotesListScreen
import com.amsavarthan.note.android.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteAppTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.NotesList) {
                        composable(Screen.NotesList) {
                            NotesListScreen(navController)
                        }
                        composable(
                            Screen.NoteDetail,
                            arguments = listOf(
                                navArgument(name = "noteId") {
                                    type = NavType.LongType
                                    defaultValue = -1L
                                }
                            )
                        ) {
                            NoteDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
