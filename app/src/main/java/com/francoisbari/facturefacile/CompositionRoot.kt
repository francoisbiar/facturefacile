package com.francoisbari.facturefacile

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.francoisbari.facturefacile.persistence.DataPersistence
import com.francoisbari.facturefacile.persistence.DataPersistenceFactory
import com.francoisbari.facturefacile.viewmodels.ContributionCalculator
import com.francoisbari.facturefacile.viewmodels.ContributionsViewModelFactory
import com.francoisbari.facturefacile.viewmodels.MainViewModelFactory

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
        this.context = context
    }

    fun getMainViewModelFactory(): ViewModelProvider.Factory {
        return MainViewModelFactory(dataPersistence)
    }

    fun getContributionsViewModelFactory(): ViewModelProvider.Factory {
        return ContributionsViewModelFactory(dataPersistence, contributionsCalculator)
    }
}