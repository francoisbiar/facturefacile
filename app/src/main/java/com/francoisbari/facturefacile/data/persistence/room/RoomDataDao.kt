package com.francoisbari.facturefacile.data.persistence.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomDataDao {
    @Query("SELECT * FROM UserInputEntity ORDER BY monthId DESC LIMIT 1")
    fun loadLatestMonth(): UserInputEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveData(userInputEntity: UserInputEntity): Long

    @Query("SELECT * FROM UserInputEntity WHERE monthId = :monthId AND year = :year LIMIT 1")
    fun getDataFromMonth(monthId: Int, year: Int): UserInputEntity?

    @Update
    fun updateData(userInputEntity: UserInputEntity)

    @Query("SELECT SUM(tjm * nbOfDays) FROM UserInputEntity WHERE year = :year")
    fun getYearlyTotal(year: Int): LiveData<Int>

    @Query("SELECT SUM(tjm * nbOfDays) FROM UserInputEntity WHERE year = :year")
    fun getYearlyTotalValue(year: Int): Int

}