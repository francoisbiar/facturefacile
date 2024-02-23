package com.francoisbari.facturefacile.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francoisbari.facturefacile.persistence.DataPersistence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ContributionsViewModel(
    private val contributionsCalculator: ContributionCalculator,
    private val dataPersistence: DataPersistence
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val totalContributionsLiveData = MutableLiveData<Int>()

    fun computeContributions() {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            // Simulate a long computation
            contributionsCalculator.getContributions(500)
            delay(2000)
            totalContributionsLiveData.postValue(13646)
            isLoading.postValue(false)
        }
    }
}