package com.francoisbari.facturefacile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.data.persistence.DataPersistence

class ContributionsViewModelFactory(
    private val dataPersistence: DataPersistence,
    private val contributionsCalculator: ContributionCalculator
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContributionsViewModel::class.java))
            return ContributionsViewModel(contributionsCalculator, dataPersistence) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
