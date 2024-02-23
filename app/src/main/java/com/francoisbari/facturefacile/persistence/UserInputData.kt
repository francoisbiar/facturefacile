package com.francoisbari.facturefacile.persistence

import com.francoisbari.facturefacile.persistence.models.Months

data class UserInputData(var nbOfDays: Int = 0, var tjm: Int = 0, var monthId: Int = Months.NONE.id)
