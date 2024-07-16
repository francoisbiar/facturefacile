package com.francoisbari.facturefacile.data.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years

interface DataPersistence {
    suspend fun loadLatestMonth(): UserInputData?
    suspend fun saveMonth(userInputDataPerMonth: UserInputData)
    suspend fun getDataFromMonth(month: Months, year: Years): UserInputData?
    fun getYearlyTotalLiveData(year: Int): LiveData<Int>
    suspend fun getYearlyTotal(year: Int): Int
    fun currentYear(): MutableLiveData<Years> = MutableLiveData(Years.default())
}

