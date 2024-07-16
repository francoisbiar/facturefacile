package com.francoisbari.facturefacile.data.persistence

import com.francoisbari.facturefacile.data.persistence.models.Months
import com.francoisbari.facturefacile.data.persistence.models.Years

data class UserInputData(
    var nbOfDays: Int = 0,
    var tjm: Int = 0,
    var month: Months = Months.NONE,
    var year: Years = Years.default()
)
