package com.francoisbari.facturefacile.presentation.viewmodels

interface ContributionCalculator {
    suspend fun getContributions(totalAmountEarned: Int): Int

}
