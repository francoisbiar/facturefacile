package com.francoisbari.facturefacile.data.persistence.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.francoisbari.facturefacile.data.persistence.DataPersistence
import com.francoisbari.facturefacile.data.persistence.UserInputData
import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years

class RoomDataPersistence(context: Context) : DataPersistence {
    private val database = Room
        .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
        .addMigrations(MIGRATION_1_2)
        .addMigrations(MIGRATION_2_3)
        .build()

    object MIGRATION_1_2 : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE UserInputEntity ADD COLUMN monthId INTEGER NOT NULL DEFAULT 0")
        }
    }

    object MIGRATION_2_3 : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // In version 3, a new column is added to the existing ones representing the
            // year of the data. Default year was 2024.
            db.execSQL("ALTER TABLE UserInputEntity ADD COLUMN year INTEGER NOT NULL DEFAULT 2024")
        }
    }

    override suspend fun loadLatestMonth(): UserInputData {
        val userInputEntity = database.userInputDao().loadLatestMonth() ?: return UserInputData()
        return userInputEntity.toData()
    }

    override suspend fun saveMonth(userInputDataPerMonth: UserInputData) {
        val userInputFromMonth =
            database.userInputDao()
                .getDataFromMonth(userInputDataPerMonth.month.id, userInputDataPerMonth.year.value)
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

    override suspend fun getDataFromMonth(month: Months, year: Years): UserInputData {
        val userInputEntity =
            database.userInputDao().getDataFromMonth(month.id, year.value) ?: return UserInputData()
        return userInputEntity.toData()
    }

    override fun getYearlyTotalLiveData(year: Int): LiveData<Int> {
        return database.userInputDao().getYearlyTotal(year)
    }

    override suspend fun getYearlyTotal(year: Int): Int {
        return database.userInputDao().getYearlyTotalValue(year)
    }

    companion object {
        private const val DATABASE_NAME = "RoomDataPersistence"
    }
}
