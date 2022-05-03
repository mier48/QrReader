package com.albertomier.qrreader.domain.model

import com.albertomier.qrreader.data.database.entities.QrEntity

data class Qr(
    val url: String,
    val date: String
)

fun QrEntity.toDomain() = Qr(url = url, date = date)