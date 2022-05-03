package com.albertomier.qrreader.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertomier.qrreader.domain.AddRecord
import com.albertomier.qrreader.domain.GetRecords
import com.albertomier.qrreader.domain.model.Qr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QrViewModel @Inject constructor(
    private val getRecords: GetRecords,
    private val addRecord: AddRecord
) :
    ViewModel() {

    var qrListModel = MutableLiveData<List<Qr>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getRecords()

            if (!result.isNullOrEmpty()) {
                qrListModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun addToFavorite(qr: Qr) {
        viewModelScope.launch {
            addRecord.invoke(qr)
        }
    }
}