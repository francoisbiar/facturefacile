package com.francoisbari.facturefacile.persistence.firestore

import android.util.Log
import androidx.lifecycle.LiveData
import com.francoisbari.facturefacile.persistence.DataPersistence
import com.francoisbari.facturefacile.persistence.UserInputData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class FirestoreDataPersistence(private val db: FirebaseFirestore) : DataPersistence {

    override suspend fun loadLatestMonth(): UserInputData {
        Log.d(TAG, "loadData: ")
        val documentSnapshot = db.collection("userInput").document("FBa").get().await()
        return documentSnapshot.toObject<UserInputData>() ?: UserInputData()
    }

    override suspend fun saveMonth(userInputDataPerMonth: UserInputData) {
        db.collection("userInput").document("FBa").set(userInputDataPerMonth).await()
    }

    override suspend fun getDataFromMonth(monthId: Int): UserInputData {
        TODO("Not yet implemented")
    }

    override fun getYearlyTotalLiveData(): LiveData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getYearlyTotal(): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "FirestoreDataPersistenc"
    }

}
