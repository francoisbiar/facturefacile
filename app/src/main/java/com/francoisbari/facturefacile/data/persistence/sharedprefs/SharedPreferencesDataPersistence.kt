package com.francoisbari.facturefacile.data.persistence.sharedprefs

import android.content.Context
import androidx.lifecycle.LiveData
import com.francoisbari.facturefacile.data.persistence.DataPersistence
import com.francoisbari.facturefacile.data.persistence.UserInputData
import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years

class SharedPreferencesDataPersistence(private val context: Context) : DataPersistence {

    companion object {
        private const val PREFS_NAME = "com.francoisbari.facturefacile.data.sharedprefs"
        private const val PREFS_KEY_NB_OF_DAYS = "nb_of_days"
        private const val PREFS_KEY_TJM = "tjm"
    }

    override suspend fun loadLatestMonth(): UserInputData {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val nbOfDays = sharedPref.getInt(PREFS_KEY_NB_OF_DAYS, 0)
        val tjm = sharedPref.getInt(PREFS_KEY_TJM, 0)
        return UserInputData(nbOfDays, tjm)
    }

    override suspend fun saveMonth(userInputDataPerMonth: UserInputData) {
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(PREFS_KEY_NB_OF_DAYS, userInputDataPerMonth.nbOfDays)
            putInt(PREFS_KEY_TJM, userInputDataPerMonth.tjm)
            apply()
        }
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
}
