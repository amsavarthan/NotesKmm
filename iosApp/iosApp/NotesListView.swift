//
//  Created by Amsavarthan Lv on 03/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NotesListView: View {

    var noteDataSource: NoteDataSource
    @ObservedObject private var viewModel: ViewModel

    init(noteDataSource: NoteDataSource) {
        self.noteDataSource = noteDataSource
        viewModel = ViewModel(notesDataSource: noteDataSource)
    }

    var body: some View {
        List($viewModel.notes) { $note in
            NavigationLink(destination: NoteDetailView(noteId: $note.id, noteDataSource: noteDataSource)) {
                NoteView(note: $note)
            }
        }
                .overlay(Group {
                    if viewModel.notes.isEmpty {
                        VStack {
                            Text(viewModel.searchQuery.isEmpty ? "No notes yet" : "No results")
                                    .font(.headline)
                                    .padding(.bottom, 0.5)
                            Text(viewModel.searchQuery.isEmpty ? "All your notes show up here" : "No results found matching \"\(viewModel.searchQuery)\"")
                                    .multilineTextAlignment(.center)
                                    .foregroundColor(.gray)
                        }
                    }
                })
                .listStyle(.plain)
                .searchable(text: $viewModel.searchQuery, prompt: "Search")
                .navigationTitle("Notes")
                .toolbar {
                    ToolbarItem {
                        NavigationLink(destination: NoteDetailView(noteDataSource: noteDataSource), isActive: $viewModel.pushView) {
                            Button {
                                viewModel.pushView.toggle()
                            } label: {
                                Image(systemName: "square.and.pencil")
                            }
                        }
                    }
                }
                .onAppear {
                    viewModel.loadNotes()
                }
    }
}

extension NotesListView {

    class ViewModel: ObservableObject {

        private var notesDataSource: NoteDataSource
        private let searchNotes = SearchNotes()

        @Published var notes = [Note]()
        private var allNotes = [Note]()

        @Published var searchQuery = "" {
            didSet {
                notes = searchNotes.execute(notes: allNotes, query: searchQuery)
            }
        }
        @Published var pushView = false

        init(notesDataSource: NoteDataSource) {
            self.notesDataSource = notesDataSource
        }

        func loadNotes() {
            DispatchQueue.main.async { [self] in
                Task {
                    do {
                        let notes = try await notesDataSource.getAllNotes()
                        allNotes = notes
                        guard searchQuery.isEmpty else {
                            self.notes = searchNotes.execute(notes: notes, query: searchQuery)
                            return
                        }
                        self.notes = notes
                    } catch {
                    }
                }
            }
        }

    }

}
