package com.francoisbari.facturefacile.core

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.data.persistence.DataPersistence
import com.francoisbari.facturefacile.data.persistence.DataPersistenceFactory
import com.francoisbari.facturefacile.data.remote.UrssafConnector
import com.francoisbari.facturefacile.presentation.viewmodels.ContributionCalculator
import com.francoisbari.facturefacile.presentation.viewmodels.ContributionsViewModelFactory
import com.francoisbari.facturefacile.presentation.viewmodels.MainViewModelFactory

@SuppressLint("StaticFieldLeak")
object CompositionRoot {
    private lateinit var context: Context
    private val dataPersistence: DataPersistence by lazy {
        DataPersistenceFactory.create(
            context, DataPersistenceFactory.DataPersistenceType.ROOM
        )
    }

    private val contributionsCalculator: ContributionCalculator by lazy {
        UrssafConnector()
    }

    fun init(context: Context) {
        CompositionRoot.context = context
    }

    fun getMainViewModelFactory(): ViewModelProvider.Factory {
        return MainViewModelFactory(dataPersistence)
    }

    fun getContributionsViewModelFactory(): ViewModelProvider.Factory {
        return ContributionsViewModelFactory(dataPersistence, contributionsCalculator)
    }
}