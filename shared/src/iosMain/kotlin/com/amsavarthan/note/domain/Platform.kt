package com.amsavarthan.note.domain

import kotlinx.datetime.LocalDateTime

actual interface CommonParcelable

actual interface CommonParceler<T>
actual object LocalDateTimeParceler : CommonParceler<LocalDateTime>