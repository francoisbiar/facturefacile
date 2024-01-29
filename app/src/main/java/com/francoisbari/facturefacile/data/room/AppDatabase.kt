package com.francoisbari.facturefacile.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInputEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInputDao(): RoomDataDao
}