//
//  Created by Amsavarthan Lv on 03/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteDetailView: View {

    var noteId: KotlinLong? = nil

    var noteDataSource: NoteDataSource
    @ObservedObject private var viewModel: ViewModel
    @Environment(\.presentationMode) private var presentationMode: Binding<PresentationMode>

    init(noteId: KotlinLong? = nil, noteDataSource: NoteDataSource) {
        self.noteId = noteId
        self.noteDataSource = noteDataSource
        viewModel = ViewModel(notesDataSource: noteDataSource)
    }

    var body: some View {
        VStack {
            TextField(text: $viewModel.noteTitle) {
                Text("Note title")
            }
                    .autocorrectionDisabled()
                    .font(.title)
                    .padding(.leading, 6)
            TextEditorWithPlaceholder(text: $viewModel.noteContent)
                    .autocorrectionDisabled()
            Spacer()
        }
                .padding()
                .navigationBarTitleDisplayMode(.inline)
                .toolbar {
                    ToolbarItemGroup(placement: .destructiveAction) {
                        Spacer()
                        Button(role: .destructive) {
                            viewModel.isConfirmationDialogPresented = true
                        } label: {
                            Image(systemName: "trash")
                        }
                    }
                }
                .confirmationDialog("Delete note", isPresented: $viewModel.isConfirmationDialogPresented) {
                    Button("Delete", role: .destructive) {
                        withAnimation {
                            viewModel.deleteNote()
                            presentationMode.wrappedValue.dismiss()
                        }
                    }
                }
                .onAppear {
                    viewModel.fetchNote(noteId)
                }
    }
}

extension NoteDetailView {

    class ViewModel: ObservableObject {

        private var notesDataSource: NoteDataSource

        @Published var isConfirmationDialogPresented = false

        @Published private(set) var note = Note.emptyNote
        @Published var noteTitle = Note.emptyNote.title {
            didSet {
                note = note.copy(title: noteTitle)
                updateNote()
            }
        }
        @Published var noteContent = Note.emptyNote.content {
            didSet {
                note = note.copy(content: noteContent)
                updateNote()
            }
        }

        init(notesDataSource: NoteDataSource) {
            self.notesDataSource = notesDataSource
        }

        func fetchNote(_ id: KotlinLong?) {
            DispatchQueue.main.async { [self] in
                Task {
                    do {
                        guard let id else {
                            return createNewNote()
                        }
                        let existingNote = try await notesDataSource.getNoteById(id: Int64(truncating: id))
                        note = existingNote!
                        noteTitle = note.title
                        noteContent = note.content
                    } catch {
                    }
                }
            }
        }

        func deleteNote() {
            DispatchQueue.main.async { [self] in
                Task {
                    do {
                        try await notesDataSource.deleteNoteById(id: Int64(truncating: note.id!))
                    } catch {
                    }
                }
            }
        }

        private func createNewNote() {
            DispatchQueue.main.async { [self] in
                Task {
                    do {
                        let id = try await notesDataSource.insertNote(note: note)
                        note = note.copy(id: id)
                    } catch {
                    }
                }
            }
        }

        private func updateNote() {
            DispatchQueue.main.async { [self] in
                Task {
                    do {
                        try await notesDataSource.insertNote(note: note)
                    } catch {
                    }
                }
            }
        }

    }

}