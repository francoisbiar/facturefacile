package com.francoisbari.facturefacile.data.room

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

    @Query("SELECT * FROM UserInputEntity WHERE monthId = :monthId LIMIT 1")
    fun getDataFromMonth(monthId: Int): UserInputEntity?

    @Update
    fun updateData(userInputEntity: UserInputEntity)
}