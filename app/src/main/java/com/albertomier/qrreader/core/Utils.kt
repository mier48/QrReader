package com.albertomier.qrreader.core

import android.os.Build
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {
    fun getCurrentDateTime(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

            return current.format(formatter)
        }

        return ""
    }
}