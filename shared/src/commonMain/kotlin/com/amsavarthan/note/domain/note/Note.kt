package com.amsavarthan.note.domain.note

import com.amsavarthan.note.domain.CommonParcelable
import com.amsavarthan.note.domain.CommonParcelize
import com.amsavarthan.note.domain.CommonTypeParceler
import com.amsavarthan.note.domain.LocalDateTimeParceler
import com.amsavarthan.note.domain.time.DateTimeUtil
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class Note(
    val id: Long? = null,
    val title: String = "Untitled",
    val content: String = "",
    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    @CommonTypeParceler<LocalDateTime, LocalDateTimeParceler>()
    val created: LocalDateTime = DateTimeUtil.now()
) : CommonParcelable