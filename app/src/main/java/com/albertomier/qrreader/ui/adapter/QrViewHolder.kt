package com.albertomier.qrreader.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.qrreader.databinding.QrBinding
import com.albertomier.qrreader.domain.model.Qr

class QrViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = QrBinding.bind(view)

    fun render(qr: Qr) {
    }

}