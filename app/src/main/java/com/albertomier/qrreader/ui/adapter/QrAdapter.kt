package com.albertomier.qrreader.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.qrreader.R
import com.albertomier.qrreader.domain.model.Qr

class QrAdapter(
    private val qrList: List<Qr>,
    private val onClickListener: (Qr) -> Unit
) : RecyclerView.Adapter<QrViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QrViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return QrViewHolder(layoutInflater.inflate(R.layout.qr, parent, false))
    }

    override fun onBindViewHolder(holder: QrViewHolder, position: Int) {
        val item = qrList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int {
        if (!qrList.isNullOrEmpty()) {
            return qrList.size
        }
        return 0
    }
}