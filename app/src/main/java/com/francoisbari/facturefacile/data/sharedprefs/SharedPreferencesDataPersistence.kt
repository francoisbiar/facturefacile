package com.francoisbari.facturefacile.data.sharedprefs

import android.content.Context
import android.util.Log
import com.francoisbari.facturefacile.data.DataPersistence
import com.francoisbari.facturefacile.data.UserInputData

class SharedPreferencesDataPersistence(private val context: Context) : DataPersistence {

    companion object {
        private const val PREFS_NAME = "com.francoisbari.facturefacile.data.sharedprefs"
        private const val PREFS_KEY_NB_OF_DAYS = "nb_of_days"
        private const val PREFS_KEY_TJM = "tjm"
    }

    override fun loadData(): UserInputData {
        Thread.dumpStack()
        Log.d("SharedPreferencesDataPersistence", "loadData")
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val nbOfDays = sharedPref.getInt(PREFS_KEY_NB_OF_DAYS, 0)
        val tjm = sharedPref.getInt(PREFS_KEY_TJM, 0)
        Log.d("SharedPreferencesDataPersistence", "loadData: $nbOfDays, $tjm")
        return UserInputData(nbOfDays, tjm)
    }

    override fun saveData(userInputData: UserInputData) {
        Thread.dumpStack()
        Log.d("SharedPreferencesDataPersistence", "saveData: $userInputData")
        val sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(PREFS_KEY_NB_OF_DAYS, userInputData.nbOfDays)
            putInt(PREFS_KEY_TJM, userInputData.tjm)
            apply()
        }
    }
}
