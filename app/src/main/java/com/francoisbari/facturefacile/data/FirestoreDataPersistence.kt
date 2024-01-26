package com.francoisbari.facturefacile.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class FirestoreDataPersistence(private val db: FirebaseFirestore) : DataPersistence {

    override suspend fun loadData(): UserInputData {
        Log.d(TAG, "loadData: ")
        val documentSnapshot = db.collection("userInput").document("FBa").get().await()
        return documentSnapshot.toObject<UserInputData>() ?: UserInputData()
    }

    override suspend fun saveData(userInputData: UserInputData) {
        db.collection("userInput").document("FBa").set(userInputData).await()
    }

    companion object {
        private const val TAG = "FirestoreDataPersistenc"
    }

}
