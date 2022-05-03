package com.albertomier.qrreader.ui.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.albertomier.qrreader.core.Utils
import com.albertomier.qrreader.databinding.FragmentMainBinding
import com.albertomier.qrreader.domain.model.Qr
import com.albertomier.qrreader.ui.viewmodel.QrViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.android.BeepManager
import com.journeyapps.barcodescanner.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val qrViewModel: QrViewModel by viewModels()

    private lateinit var barcodeView: DecoratedBarcodeView
    private lateinit var beepManager: BeepManager

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            if (result == null || result.text == null) {
                return
            }

            val qr = Qr(url = result.text, date = Utils.getCurrentDateTime())
            qrViewModel.addToFavorite(qr)

            barcodeView.setStatusText(result.text)
            beepManager.playBeepSoundAndVibrate()

            if (result.text.startsWith("http://") || result.text.startsWith("https://")) {
                val defaultBrowser = Intent(Intent.ACTION_VIEW)
                defaultBrowser.data = Uri.parse(result.text)
                startActivity(defaultBrowser)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initScanner()

        MobileAds.initialize(activity) {}

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

        return root
    }

    private fun initScanner() {
//        val barcodeLauncher =
//            requireActivity().registerForActivityResult(ScanContract()) { result ->
//                if (result.contents == null) {
//                    Toast.makeText(activity, "Cancelled", Toast.LENGTH_LONG).show();
//                } else {
//                    val qr = Qr(url = result.contents, date = Utils.getCurrentDateTime())
//                    qrViewModel.addToFavorite(qr)
//                    Toast.makeText(activity, "Scanned: " + result.contents, Toast.LENGTH_LONG)
//                        .show();
//                }
//            }

        barcodeView = binding.qrView

        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.QR_CODE)
        options.setPrompt("QR Scanner")
        options.setTorchEnabled(false)
        options.setBeepEnabled(true)
        options.setOrientationLocked(true)
        options.setBarcodeImageEnabled(true)

        val formats: Collection<BarcodeFormat> = mutableListOf(BarcodeFormat.QR_CODE)
        val defaultFactory = DefaultDecoderFactory(formats)
        barcodeView.barcodeView.decoderFactory = defaultFactory
        barcodeView.initializeFromIntent(requireActivity().intent)
        barcodeView.decodeContinuous(callback)

        beepManager = BeepManager(activity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume();
        barcodeView.resume();
    }

    override fun onPause() {
        super.onPause();
        barcodeView.pause();
    }

    fun pause(view: View) {
        barcodeView.pause();
    }

    fun resume(view: View) {
        barcodeView.resume();
    }
}