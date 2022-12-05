package com.amsavarthan.note.domain

import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class CommonParcelize()

expect interface CommonParcelable

@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Retention(AnnotationRetention.SOURCE)
@Repeatable
@Target(AnnotationTarget.CLASS, AnnotationTarget.PROPERTY)
expect annotation class CommonTypeParceler<T, P : CommonParceler<in T>>()

expect interface CommonParceler<T>

expect object LocalDateTimeParceler: CommonParceler<LocalDateTime>