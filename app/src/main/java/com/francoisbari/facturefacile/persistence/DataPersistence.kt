package com.francoisbari.facturefacile.persistence

import androidx.lifecycle.LiveData

interface DataPersistence {
    suspend fun loadLatestMonth(): UserInputData?
    suspend fun saveMonth(userInputDataPerMonth: UserInputData)
    suspend fun getDataFromMonth(monthId: Int): UserInputData?
    fun getYearlyTotalLiveData(): LiveData<Int>
    suspend fun getYearlyTotal(): Int
}
