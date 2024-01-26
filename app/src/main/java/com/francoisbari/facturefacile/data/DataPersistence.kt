package com.francoisbari.facturefacile.data

interface DataPersistence {
    fun loadData(): UserInputData
    fun saveData(userInputData: UserInputData)
}
