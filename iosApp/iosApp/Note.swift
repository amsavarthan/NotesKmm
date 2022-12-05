//
//  Created by Amsavarthan Lv on 03/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared

extension Note: Identifiable {

    static let emptyNote = Note(
            id: nil,
            title: "Untitled",
            content: "",
            created: DateTimeUtil().now()
    )

    func copy(id: KotlinLong? = nil, title: String? = nil, content: String? = nil) -> Note {
        Note(id: id ?? self.id, title: title ?? self.title, content: content ?? self.content, created: self.created)
    }

}