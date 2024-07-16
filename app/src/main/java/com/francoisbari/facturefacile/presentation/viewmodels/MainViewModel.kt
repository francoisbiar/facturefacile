package com.francoisbari.facturefacile.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francoisbari.facturefacile.data.persistence.DataPersistence
import com.francoisbari.facturefacile.data.persistence.UserInputData
import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val dataPersistence: DataPersistence) : ViewModel() {
    private var currentMonth = Months.NONE
    private var currentYear = Years.default()

    val nbOfDaysLiveData = MutableLiveData<String>()
    val tjmLiveData = MutableLiveData<String>()
    var yearlyTotalLiveData = dataPersistence.getYearlyTotalLiveData(currentYear.value)

    val chargesLiveData = MutableLiveData<String>()

    init {
        loadMonthlyData()
    }

    fun addOneDayClicked() {
        val currentDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0
        nbOfDaysLiveData.value = (currentDays + 1).toString()
    }

    private fun loadMonthlyData() {
        viewModelScope.launch(Dispatchers.IO) {
            val storedInfos = dataPersistence.loadLatestMonth() ?: return@launch
            nbOfDaysLiveData.postValue(storedInfos.nbOfDays.toString())
            tjmLiveData.postValue(storedInfos.tjm.toString())
            currentMonth = storedInfos.month
            currentYear = storedInfos.year
            yearlyTotalLiveData = dataPersistence.getYearlyTotalLiveData(currentYear.value)
            dataPersistence.currentYear().postValue(currentYear)
        }
    }

    private fun saveMonthlyData() {
        val infosToStore = UserInputData(
            nbOfDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0,
            tjm = tjmLiveData.value?.toIntOrNull() ?: 0,
            month = currentMonth,
            year = currentYear
        )
        viewModelScope.launch(Dispatchers.IO) { dataPersistence.saveMonth(infosToStore) }
    }

    val totalLiveData: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(nbOfDaysLiveData) { value = computeTotal() }
        addSource(tjmLiveData) { value = computeTotal() }
        // Save the data each time total is computed.
        addSource(this) { saveMonthlyData() }
    }

    private fun computeTotal(): Int {
        val nbOfDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0
        val tjm = tjmLiveData.value?.toIntOrNull() ?: 0
        return nbOfDays * tjm
    }

    fun selectMonth(month: String) {
        // Save the data of the current month.
        val infosToStorePerMonth = UserInputData(
            nbOfDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0,
            tjm = tjmLiveData.value?.toIntOrNull() ?: 0,
            month = currentMonth,
            year = currentYear
        )
        viewModelScope.launch(Dispatchers.IO) { dataPersistence.saveMonth(infosToStorePerMonth) }

        // Get the data from the selected month.
        currentMonth = Months.fromString(month)

        viewModelScope.launch(Dispatchers.IO) {
            val storedInfosPerMonth =
                dataPersistence.getDataFromMonth(currentMonth, currentYear) ?: return@launch
            nbOfDaysLiveData.postValue(storedInfosPerMonth.nbOfDays.toString())
            tjmLiveData.postValue(storedInfosPerMonth.tjm.toString())
        }
    }

    fun selectYear(year: String) {
        // Save the data of the current month before switch to another.
        val infosToStorePerMonth = UserInputData(
            nbOfDays = nbOfDaysLiveData.value?.toIntOrNull() ?: 0,
            tjm = tjmLiveData.value?.toIntOrNull() ?: 0,
            month = currentMonth,
            year = currentYear
        )
        viewModelScope.launch(Dispatchers.IO) { dataPersistence.saveMonth(infosToStorePerMonth) }

        currentYear = Years.fromString(year) ?: Years.default()
        yearlyTotalLiveData = dataPersistence.getYearlyTotalLiveData(currentYear.value)
        dataPersistence.currentYear().postValue(currentYear)

        // Get the data from the selected year.
        viewModelScope.launch(Dispatchers.IO) {
            val storedInfosPerMonth =
                dataPersistence.getDataFromMonth(currentMonth, currentYear) ?: return@launch
            nbOfDaysLiveData.postValue(storedInfosPerMonth.nbOfDays.toString())
            tjmLiveData.postValue(storedInfosPerMonth.tjm.toString())
        }
    }
}