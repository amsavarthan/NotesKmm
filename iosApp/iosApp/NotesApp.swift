//
//  Created by Amsavarthan Lv on 03/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@main
struct iOSApp: App {

    private let databaseModule = DatabaseModule()

    var body: some Scene {
        WindowGroup {
            NavigationView {
                NotesListView(noteDataSource: databaseModule.noteDataSource)
            }
        }
    }
}
