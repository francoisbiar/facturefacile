package com.francoisbari.facturefacile.data

import android.content.Context
import com.francoisbari.facturefacile.data.room.RoomDataPersistence
import com.francoisbari.facturefacile.data.sharedprefs.SharedPreferencesDataPersistence

class DataPersistenceFactory {
    companion object {
        fun create(context: Context, type: DataPersistenceType): DataPersistence {
            return when (type) {
                DataPersistenceType.SHARED_PREFERENCES -> SharedPreferencesDataPersistence(context)
                DataPersistenceType.ROOM -> RoomDataPersistence(context)
            }
        }
    }

    enum class DataPersistenceType {
        SHARED_PREFERENCES,
        ROOM
    }
}
