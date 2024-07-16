package com.francoisbari.facturefacile.data.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInputEntity::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInputDao(): RoomDataDao
}