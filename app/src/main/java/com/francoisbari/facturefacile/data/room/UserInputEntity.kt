package com.francoisbari.facturefacile.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.francoisbari.facturefacile.data.UserInputData

@Entity
data class UserInputEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val nbOfDays: Int,
    val tjm: Int,
    @ColumnInfo(index = true) val monthId: Int
)

fun UserInputEntity.toData() = UserInputData(nbOfDays, tjm, monthId)
fun UserInputData.toEntity() = UserInputEntity(nbOfDays = nbOfDays, tjm = tjm, monthId = monthId)


