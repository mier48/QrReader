package com.albertomier.qrreader.domain

import com.albertomier.qrreader.data.QrRepository
import com.albertomier.qrreader.data.database.entities.toDatabase
import com.albertomier.qrreader.domain.model.Qr
import javax.inject.Inject

class AddRecord @Inject constructor(private val repository: QrRepository) {

    suspend operator fun invoke(qr: Qr) {
        return repository.addRecord(qr.toDatabase())
    }
}