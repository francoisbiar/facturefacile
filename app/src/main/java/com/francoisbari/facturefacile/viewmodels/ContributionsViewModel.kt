package com.francoisbari.facturefacile.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.francoisbari.facturefacile.persistence.DataPersistence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContributionsViewModel(
    private val contributionsCalculator: ContributionCalculator,
    private val dataPersistence: DataPersistence
) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    private val _totalContributionsLiveData = MediatorLiveData<Int?>()
    val totalContributionsLiveData = _totalContributionsLiveData.map { it ?: 0 }

    init {
        _totalContributionsLiveData.addSource(dataPersistence.getYearlyTotalLiveData()) {
            if (it >= 0) {
                computeContributions(it)
            } else {
                _totalContributionsLiveData.value = null
            }
        }
    }


    private fun computeContributions(totalAmountEarned: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoading.postValue(true)
            val totalAmount = contributionsCalculator.getContributions(totalAmountEarned)
            _totalContributionsLiveData.postValue(totalAmount)
            isLoading.postValue(false)
        }
    }
}