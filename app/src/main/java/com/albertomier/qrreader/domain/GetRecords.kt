package com.albertomier.qrreader.domain

import com.albertomier.qrreader.data.QrRepository
import com.albertomier.qrreader.domain.model.Qr
import javax.inject.Inject

class GetRecords @Inject constructor(private val repository: QrRepository) {

    suspend operator fun invoke(): List<Qr> {
        return repository.getAllQr()
    }
}