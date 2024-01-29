package com.francoisbari.facturefacile.data

import android.content.Context
import com.francoisbari.facturefacile.data.firestore.FirestoreDataPersistence
import com.francoisbari.facturefacile.data.room.RoomDataPersistence
import com.francoisbari.facturefacile.data.sharedprefs.SharedPreferencesDataPersistence
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class DataPersistenceFactory {
    companion object {
        fun create(context: Context, type: DataPersistenceType): DataPersistence {
            return when (type) {
                DataPersistenceType.SHARED_PREFERENCES -> SharedPreferencesDataPersistence(context)
                DataPersistenceType.ROOM -> RoomDataPersistence(context)
                DataPersistenceType.FIRESTORE -> FirestoreDataPersistence(Firebase.firestore)
            }
        }
    }

    enum class DataPersistenceType {
        SHARED_PREFERENCES,
        ROOM,
        FIRESTORE
    }
}
