package com.francoisbari.facturefacile.remote

import com.francoisbari.facturefacile.remote.models.Expression
import com.francoisbari.facturefacile.remote.models.MonEntrepriseApi
import com.francoisbari.facturefacile.remote.models.PostRequestBody
import com.francoisbari.facturefacile.remote.models.Situation
import com.francoisbari.facturefacile.remote.unsafeokhttpclient.UnsafeOkHttpClient
import com.francoisbari.facturefacile.viewmodels.ContributionCalculator
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class UrssafConnector : ContributionCalculator {
    override suspend fun getContributions(totalAmountEarned: Int): Int {

        val requestBody = PostRequestBody(
            situation = Situation(
                imposition = "IR",
                chiffreAffaires = totalAmountEarned,
                charges = 0,
                activiteNatureLiberaleReglementee = "non",
                activiteNature = "'libérale'",
                associes = "'unique'",
                categorieJuridique = "'SARL'"
            ),
            expressions = listOf(
                Expression(
                    valeur = "dirigeant . indépendant . cotisations et contributions",
                    unite = "€/an"
                )
            )
        )

        val response = getRetrofitInstance().evaluate(requestBody)
        // The response is a list of Evaluate objects, order corresponds to the one sent.
        return response.evaluate.first().nodeValue
    }

    private fun getRetrofitInstance(): MonEntrepriseApi {

        // Todo stop using unsafeOkHttpClient as soon as issue with SSL certificate is resolved
        // https://stackoverflow.com/questions/6825226/trust-anchor-not-found-for-android-ssl-connection

        return Retrofit.Builder()
            .baseUrl(URSSAF_API_URL)
            .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MonEntrepriseApi::class.java)
    }

    companion object {
        private const val URSSAF_API_URL = "https://mon-entreprise.urssaf.fr"
    }
}
