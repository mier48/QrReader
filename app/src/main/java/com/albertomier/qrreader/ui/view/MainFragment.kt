package com.albertomier.qrreader.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.albertomier.qrreader.databinding.FragmentMainBinding
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initScanner()

        return root
    }

    private fun initScanner() {
        val barcodeLauncher: DecoratedBarcodeView = binding.qrView
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("QR Scanner")
        options.setTorchEnabled(false)
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)
        options.setBarcodeImageEnabled(true)
        barcodeLauncher.barcodeView.resume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}