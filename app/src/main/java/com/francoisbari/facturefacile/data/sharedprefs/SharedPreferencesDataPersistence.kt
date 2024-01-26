package com.francoisbari.facturefacile.data.sharedprefs

import android.content.Context
import com.francoisbari.facturefacile.data.DataPersistence
import com.francoisbari.facturefacile.data.UserInputData

class SharedPreferencesDataPersistence(context: Context) :
    DataPersistence {
    override fun loadData(): UserInputData {
        return UserInputData(18, 475)
    }

    override fun saveData(userInputData: UserInputData) {
    }

}
