package com.amsavarthan.note.data.note

import com.amsavarthan.note.domain.note.Note
import com.amsavarthan.note.domain.time.DateTimeUtil
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.asNote() = Note(
    id = id,
    title = title,
    content = content,
    created = DateTimeUtil.toLocalDateTime(created)
)