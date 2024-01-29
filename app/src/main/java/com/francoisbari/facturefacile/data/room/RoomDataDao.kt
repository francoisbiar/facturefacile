package com.francoisbari.facturefacile.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomDataDao {
    @Query("SELECT * FROM UserInputEntity LIMIT 1")
    fun loadData(): UserInputEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveData(userInputEntity: UserInputEntity)
}