package com.albertomier.qrreader.data

import com.albertomier.qrreader.data.database.dao.QrDao
import com.albertomier.qrreader.data.database.entities.QrEntity
import com.albertomier.qrreader.domain.model.Qr
import com.albertomier.qrreader.domain.model.toDomain
import javax.inject.Inject

class QrRepository @Inject constructor(
    private val qrDao: QrDao
) {

    suspend fun getAllQr(): List<Qr> {
        val response = qrDao.getAllQr()
        return response.map { it.toDomain() }
    }

    suspend fun addRecord(qr: QrEntity) {
        qrDao.insert(qr)
    }
}