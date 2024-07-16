package com.francoisbari.facturefacile.data.persistence.models

enum class Years(val value: Int) {
    Y2022(2022),
    Y2023(2023),
    Y2024(2024);

    companion object {

        fun default(): Years {
            return Y2024
        }

        fun fromString(year: String): Years? {
            return entries.find { it.value.toString() == year }
        }


        fun toArrayList(): Array<String> {
            return entries
                .map { it.value.toString() }
                .toTypedArray()
        }

        fun fromInt(year: Int): Years {
            return entries.find { it.value == year } ?: default()
        }
    }
}