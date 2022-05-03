package com.albertomier.qrreader.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.albertomier.qrreader.data.database.entities.QrEntity

@Dao
interface QrDao {

    @Query("SELECT * FROM qr_records ORDER BY id DESC")
    suspend fun getAllQr(): List<QrEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(qr: QrEntity)
}