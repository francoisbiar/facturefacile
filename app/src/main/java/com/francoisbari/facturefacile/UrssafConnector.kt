package com.francoisbari.facturefacile

import com.francoisbari.facturefacile.viewmodels.ContributionCalculator
import kotlinx.coroutines.delay

class UrssafConnector : ContributionCalculator {
    override suspend fun getContributions(totalAmountEarned: Int) {
        delay(5000)
    }

}
