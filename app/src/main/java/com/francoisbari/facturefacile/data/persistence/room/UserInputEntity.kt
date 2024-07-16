package com.francoisbari.facturefacile.data.persistence.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.francoisbari.facturefacile.data.persistence.UserInputData
import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years

@Entity
data class UserInputEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val nbOfDays: Int,
    val tjm: Int,
    @ColumnInfo(index = true) val monthId: Int,
    val year: Int
)

fun UserInputEntity.toData() = UserInputData(nbOfDays, tjm, Months.fromId(monthId), Years.fromInt(year))
fun UserInputData.toEntity() =
    UserInputEntity(nbOfDays = nbOfDays, tjm = tjm, monthId = month.id, year = year.value)


