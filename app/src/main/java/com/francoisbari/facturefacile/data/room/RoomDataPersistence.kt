package com.francoisbari.facturefacile.data.room

import android.content.Context
import androidx.room.Room
import com.francoisbari.facturefacile.data.DataPersistence
import com.francoisbari.facturefacile.data.UserInputData

class RoomDataPersistence(context: Context) : DataPersistence {
    private val database =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    private var uid: Int = 0

    override suspend fun loadData(): UserInputData {
        val userInputEntity = database.userInputDao().loadData()
        if (userInputEntity == null) {
            uid = 0
            return UserInputData()
        }
        return userInputEntity.toData()
    }

    override suspend fun saveData(userInputData: UserInputData) {
        val userInputEntity = userInputData.toEntity(uid)
        database.userInputDao().saveData(userInputEntity)
    }

    companion object {
        private const val DATABASE_NAME = "RoomDataPersistence"
    }

}
