package com.amsavarthan.note.domain

import android.os.Parcel
import android.os.Parcelable
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.TypeParceler

actual typealias CommonParcelize = Parcelize
actual typealias CommonParcelable = Parcelable

actual typealias CommonParceler<T> = Parceler<T>
actual typealias CommonTypeParceler<T,P> = TypeParceler<T, P>

actual object LocalDateTimeParceler : Parceler<LocalDateTime> {
    override fun create(parcel: Parcel): LocalDateTime {
        val date = parcel.readString()
        return date?.toLocalDateTime()
            ?: LocalDateTime(0, 0, 0, 0, 0)
    }

    override fun LocalDateTime.write(parcel: Parcel, flags: Int) {
        parcel.writeString(this.toString())
    }
}