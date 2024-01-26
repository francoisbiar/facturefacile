package com.francoisbari.facturefacile.data

interface DataPersistence {
    suspend fun loadData(): UserInputData
    suspend fun saveData(userInputData: UserInputData)
}
