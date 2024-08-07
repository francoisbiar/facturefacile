package com.francoisbari.facturefacile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.data.persistence.DataPersistence

class MainViewModelFactory(private val dataPersistence: DataPersistence) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(dataPersistence) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
