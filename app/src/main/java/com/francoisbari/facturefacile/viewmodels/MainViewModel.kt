package com.francoisbari.facturefacile.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francoisbari.facturefacile.data.DataPersistence
import com.francoisbari.facturefacile.data.UserInputData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val dataPersistence: DataPersistence) : ViewModel() {

    val nbOfDaysLiveData = MutableLiveData<String>()
    val tjmLiveData = MutableLiveData<String>()
    private val _computeContributionsClicked = MutableLiveData<Boolean>()
    val computeContributionsLiveData: LiveData<Boolean> = _computeContributionsClicked
    val totalContributionsLiveData = MutableLiveData<Int>()

    fun addOneDayClicked() {
        val currentDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0
        nbOfDaysLiveData.value = (currentDays + 1).toString()
    }

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val storedInfos = dataPersistence.loadData()
            nbOfDaysLiveData.postValue(storedInfos.nbOfDays.toString())
            tjmLiveData.postValue(storedInfos.tjm.toString())
        }
    }

    fun saveData() {
        val infosToStore = UserInputData(
            nbOfDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0,
            tjm = tjmLiveData.value?.toIntOrNull() ?: 0
        )
        viewModelScope.launch(Dispatchers.IO) { dataPersistence.saveData(infosToStore) }
    }


    val totalLiveData: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(nbOfDaysLiveData) { value = computeTotal() }
        addSource(tjmLiveData) { value = computeTotal() }

    }

    private fun computeTotal(): Int {
        val nbOfDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0
        val tjm = tjmLiveData.value?.toIntOrNull() ?: 0
        return nbOfDays * tjm
    }

    fun computeContributionsClicked() {
        if (nbOfDaysLiveData.value.isNullOrBlank() || tjmLiveData.value.isNullOrBlank()) return
        _computeContributionsClicked.value = true
        val totalEarned = totalLiveData.value ?: 0
        val totalContributions = totalEarned * 0.22 // TODO
        totalContributionsLiveData.value = totalContributions.toInt()
    }
}