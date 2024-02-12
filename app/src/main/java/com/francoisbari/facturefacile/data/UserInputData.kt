package com.francoisbari.facturefacile.data

import com.francoisbari.facturefacile.data.models.Months

data class UserInputData(var nbOfDays: Int = 0, var tjm: Int = 0, var monthId: Int = Months.NONE.id)
