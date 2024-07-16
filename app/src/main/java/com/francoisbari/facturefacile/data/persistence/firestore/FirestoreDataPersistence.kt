package com.francoisbari.facturefacile.data.persistence.firestore

import android.util.Log
import androidx.lifecycle.LiveData
import com.francoisbari.facturefacile.data.persistence.DataPersistence
import com.francoisbari.facturefacile.data.persistence.UserInputData
import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years
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

    override suspend fun getDataFromMonth(month: Months, year: Years): UserInputData? {
        TODO("Not yet implemented")
    }

    override fun getYearlyTotalLiveData(year: Int): LiveData<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getYearlyTotal(year: Int): Int {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "FirestoreDataPersistenc"
    }

}
