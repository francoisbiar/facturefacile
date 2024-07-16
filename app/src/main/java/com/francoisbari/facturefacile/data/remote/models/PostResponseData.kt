package com.francoisbari.facturefacile.data.remote.models

internal data class ResponseData(
    val responseCachedAt: Long,
    val cacheExpiresAt: Long,
    val evaluate: List<Evaluate>,
    val warnings: List<Warning>
)

internal data class Evaluate(
    val nodeValue: Int,
    val unit: Unit,
    val missingVariables: Map<String, Int>
)

internal data class Unit(
    val numerators: List<String>,
    val denominators: List<String>
)

internal data class Warning(
    val message: String
)
