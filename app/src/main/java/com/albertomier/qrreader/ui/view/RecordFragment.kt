package com.albertomier.qrreader.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertomier.qrreader.databinding.FragmentRecordsBinding
import com.albertomier.qrreader.ui.adapter.QrAdapter
import com.albertomier.qrreader.ui.viewmodel.QrViewModel
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
            binding.recordsList.adapter = QrAdapter(it)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}