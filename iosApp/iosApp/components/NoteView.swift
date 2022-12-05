//
//  Created by Amsavarthan Lv on 03/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NoteView: View {

    @Binding var note: Note

    var body: some View {
        VStack(alignment: .leading) {
            Text(note.title)
                    .padding(.bottom, note.content.isEmpty ? 4 : 1)
            if (!note.content.isEmpty) {
                Text(note.content)
                        .foregroundColor(.gray)
                        .padding(.bottom, 4)
            }

            Text(DateTimeUtil().formatAsDate(dateTime: note.created))
                    .font(.caption)
        }
    }
}
