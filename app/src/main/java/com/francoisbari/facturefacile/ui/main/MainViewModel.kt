package com.francoisbari.facturefacile.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.francoisbari.facturefacile.data.DataPersistence
import com.francoisbari.facturefacile.data.UserInputData

class MainViewModel(private val dataPersistence: DataPersistence) : ViewModel() {

    fun setNumberOfDays(nbOfDays: Int) {
        // Update only if the value is different
        if (_nbOfDays.value != nbOfDays) _nbOfDays.value = nbOfDays
    }

    fun setTjm(tjm: Int) {
        if (_tjm.value != tjm) _tjm.value = tjm
    }

    fun addOneDayClicked() {
        _nbOfDays.value = (_nbOfDays.value ?: 0) + 1
    }

    fun loadData() {
        val storedInfos = dataPersistence.loadData()
        _nbOfDays.value = storedInfos.nbOfDays
        _tjm.value = storedInfos.tjm
    }

    fun saveData() {
        val infosToStore = UserInputData(
            nbOfDays = _nbOfDays.value ?: 0, tjm = _tjm.value ?: 0
        )
        dataPersistence.saveData(infosToStore)
    }

    private val _nbOfDays = MutableLiveData<Int>()
    val nbOfDays: LiveData<Int> = _nbOfDays

    private val _tjm = MutableLiveData<Int>()
    val tjm: LiveData<Int> = _tjm

    val total: LiveData<Int> = MediatorLiveData<Int>().apply {
        addSource(_nbOfDays) { value = it * (_tjm.value ?: 0) }
        addSource(_tjm) { value = it * (_nbOfDays.value ?: 0) }
    }
}