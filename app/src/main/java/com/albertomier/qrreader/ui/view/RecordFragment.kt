package com.albertomier.qrreader.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomier.qrreader.databinding.FragmentRecordsBinding
import com.albertomier.qrreader.domain.model.Qr
import com.albertomier.qrreader.ui.adapter.QrAdapter
import com.albertomier.qrreader.ui.viewmodel.QrViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordFragment : Fragment() {

    private var _binding: FragmentRecordsBinding? = null
    private val binding get() = _binding!!

    private val qrViewModel: QrViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val manager = LinearLayoutManager(activity)
        binding.recordsList.layoutManager = manager

        qrViewModel.onCreate()

        qrViewModel.qrListModel.observe(viewLifecycleOwner, Observer {
            binding.recordsList.adapter = QrAdapter(it, { qr -> onQrSelected(qr) })
        })

        MobileAds.initialize(activity) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        return root
    }

    private fun onQrSelected(qr: Qr) {
        if (qr.url.startsWith("http://") || qr.url.startsWith("https://")) {
            val defaultBrowser = Intent(Intent.ACTION_VIEW)
            defaultBrowser.data = Uri.parse(qr.url)
            startActivity(defaultBrowser)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}