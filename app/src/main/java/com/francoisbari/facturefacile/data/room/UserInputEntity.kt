package com.francoisbari.facturefacile.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.francoisbari.facturefacile.data.UserInputData

@Entity
data class UserInputEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0, val nbOfDays: Int, val tjm: Int
)

fun UserInputEntity.toData() = UserInputData(nbOfDays, tjm)
fun UserInputData.toEntity(uid: Int) = UserInputEntity(uid, nbOfDays, tjm)

