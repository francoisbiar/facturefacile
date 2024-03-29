package com.francoisbari.facturefacile.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInputEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInputDao(): RoomDataDao
}