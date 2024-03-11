package com.francoisbari.facturefacile.remote.models

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface MonEntrepriseApi {

    @Headers("Content-Type: application/json")
    @POST("api/v1/evaluate")
    suspend fun evaluate(@Body body: PostRequestBody): ResponseData
}