package com.francoisbari.facturefacile.viewmodels

interface ContributionCalculator {
    suspend fun getContributions(totalAmountEarned: Int)

}
