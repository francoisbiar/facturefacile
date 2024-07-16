package com.francoisbari.facturefacile.data.remote.models

import com.squareup.moshi.Json

internal data class PostRequestBody(
    @field:Json(name = "situation")
    val situation: Situation,
    @field:Json(name = "expressions")
    val expressions: List<Expression>
)

internal data class Situation(
    @field:Json(name = "entreprise . imposition")
    val imposition: String,

    @field:Json(name = "entreprise . chiffre d'affaires")
    var chiffreAffaires: Int, // This field is mutable

    @field:Json(name = "entreprise . charges")
    val charges: Int,

    @field:Json(name = "entreprise . activité . nature . libérale . réglementée")
    val activiteNatureLiberaleReglementee: String,

    @field:Json(name = "entreprise . activité . nature")
    val activiteNature: String,

    @field:Json(name = "entreprise . associés")
    val associes: String,

    @field:Json(name = "entreprise . catégorie juridique")
    val categorieJuridique: String
)

internal data class Expression(
    @field:Json(name = "valeur")
    val valeur: String,
    @field:Json(name = "unité")
    val unite: String
)




