package com.francoisbari.facturefacile.data

import androidx.lifecycle.LiveData

interface DataPersistence {
    suspend fun loadLatestMonth(): UserInputData?
    suspend fun saveMonth(userInputDataPerMonth: UserInputData)
    suspend fun getDataFromMonth(monthId: Int): UserInputData?
    fun getYearlyTotalLiveData(): LiveData<Int>
}
