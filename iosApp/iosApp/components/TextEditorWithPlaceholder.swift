//
//  Created by Amsavarthan Lv on 03/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TextEditorWithPlaceholder: View {
    @Binding var text: String

    var body: some View {
        ZStack(alignment: .leading) {
            if text.isEmpty {
                VStack {
                    Text("Enter content in here")
                            .padding(.top, 10)
                            .padding(.leading, 6)
                            .opacity(0.6)
                    Spacer()
                }
            }

            VStack {
                TextEditor(text: $text)
                        .frame(minHeight: 150, maxHeight: 300)
                        .opacity(text.isEmpty ? 0.85 : 1)
                Spacer()
            }
        }
    }
}