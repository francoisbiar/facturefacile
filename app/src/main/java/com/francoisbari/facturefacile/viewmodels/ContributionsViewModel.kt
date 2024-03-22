package com.francoisbari.facturefacile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francoisbari.facturefacile.persistence.DataPersistence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContributionsViewModel(
    private val contributionsCalculator: ContributionCalculator,
    private val dataPersistence: DataPersistence
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    private val _totalContributionsLiveData = MutableLiveData(0)
    val totalContributionsLiveData: LiveData<Int> = _totalContributionsLiveData

    fun computeContributionsClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            val totalAmountEarned = dataPersistence.getYearlyTotal()
            isLoading.postValue(true)
            val totalAmount = contributionsCalculator.getContributions(totalAmountEarned)
            _totalContributionsLiveData.postValue(totalAmount)
            isLoading.postValue(false)
        }
    }
}