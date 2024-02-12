package com.francoisbari.facturefacile.data

interface DataPersistence {
    suspend fun loadLatestMonth(): UserInputData?
    suspend fun saveMonth(userInputDataPerMonth: UserInputData)
    suspend fun getDataFromMonth(monthId: Int): UserInputData?
}
