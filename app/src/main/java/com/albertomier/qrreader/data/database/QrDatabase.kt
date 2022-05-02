package com.albertomier.qrreader.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.albertomier.qrreader.data.database.dao.QrDao
import com.albertomier.qrreader.data.database.entities.QrEntity

@Database(entities = [QrEntity::class], version = 1)
abstract class QrDatabase : RoomDatabase() {
    abstract fun getQrDao(): QrDao
}