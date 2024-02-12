package com.francoisbari.facturefacile.data.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.francoisbari.facturefacile.data.DataPersistence
import com.francoisbari.facturefacile.data.UserInputData

class RoomDataPersistence(context: Context) : DataPersistence {
    private val database = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .addMigrations(MIGRATION_1_2)
        .build()

    object MIGRATION_1_2 : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE UserInputEntity ADD COLUMN monthId INTEGER NOT NULL DEFAULT 0")
        }

    }

    override suspend fun loadLatestMonth(): UserInputData {
        val userInputEntity = database.userInputDao().loadLatestMonth() ?: return UserInputData()
        return userInputEntity.toData()
    }

    override suspend fun saveMonth(userInputDataPerMonth: UserInputData) {
        val userInputFromMonth =
            database.userInputDao().getDataFromMonth(userInputDataPerMonth.monthId)
        if (userInputFromMonth != null) {
            val updatedEntity = userInputFromMonth.copy(
                nbOfDays = userInputDataPerMonth.nbOfDays,
                tjm = userInputDataPerMonth.tjm
            )
            database.userInputDao().updateData(updatedEntity)
        } else {
            database.userInputDao().saveData(userInputDataPerMonth.toEntity())
        }
    }

    override suspend fun getDataFromMonth(monthId: Int): UserInputData {
        val userInputEntity =
            database.userInputDao().getDataFromMonth(monthId) ?: return UserInputData()
        return userInputEntity.toData()
    }

    override fun getYearlyTotalLiveData(): LiveData<Int> {
        return database.userInputDao().getYearlyTotal()
    }

    companion object {
        private const val DATABASE_NAME = "RoomDataPersistence"
    }
}
