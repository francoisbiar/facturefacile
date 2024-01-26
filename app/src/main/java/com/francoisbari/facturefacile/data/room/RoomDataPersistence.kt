package com.francoisbari.facturefacile.data.room

import android.content.Context
import com.francoisbari.facturefacile.data.DataPersistence
import com.francoisbari.facturefacile.data.UserInputData

class RoomDataPersistence(context: Context) :
    DataPersistence {
    override suspend fun loadData(): UserInputData {
        TODO("Not yet implemented")
    }

    override suspend fun saveData(userInputData: UserInputData) {
        TODO("Not yet implemented")
    }

}
