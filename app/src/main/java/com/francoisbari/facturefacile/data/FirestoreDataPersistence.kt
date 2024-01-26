package com.francoisbari.facturefacile.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class FirestoreDataPersistence(private val db: FirebaseFirestore) : DataPersistence {

    override fun loadData(): UserInputData {
        Log.d(TAG, "loadData: ")
        db.collection("userInput").document("FBa")
            .get()
            .addOnSuccessListener {
                val userInput = it.toObject<UserInputData>()
                Log.d(TAG, "loadData: $userInput")
            }
        return UserInputData(0, 0)
    }

    override fun saveData(userInputData: UserInputData) {
        db.collection("userInput").document("FBa")
            .set(userInputData)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot added ")
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Error adding document: $e")
            }
    }

    companion object {
        private const val TAG = "FirestoreDataPersistenc"
    }

}
