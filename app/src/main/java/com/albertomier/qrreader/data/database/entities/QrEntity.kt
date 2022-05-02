package com.albertomier.qrreader.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.albertomier.qrreader.domain.model.Qr

@Entity(tableName = "qr_records")
class QrEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Int = 0,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "date") var date: String)

fun Qr.toDatabase() = QrEntity(url = url, date = date)
