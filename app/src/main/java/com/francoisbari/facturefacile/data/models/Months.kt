package com.francoisbari.facturefacile.data.models

enum class Months(val id: Int, val stringValue: String) {
    JANUARY(1, "Janvier"),
    FEBRUARY(2, "Février"),
    MARCH(3, "Mars"),
    APRIL(4, "Avril"),
    MAY(5, "Mai"),
    JUNE(6, "Juin"),
    JULY(7, "Juillet"),
    AUGUST(8, "Août"),
    SEPTEMBER(9, "Septembre"),
    OCTOBER(10, "Octobre"),
    NOVEMBER(11, "Novembre"),
    DECEMBER(12, "Décembre"),
    NONE(0, "None");

    companion object {
        fun toArrayList(): Array<String> {
            // returns the string value of the Month to an array.
            return entries.map { it.stringValue }
                .filter { it != NONE.stringValue }
                .toTypedArray()
        }

        fun fromId(monthId: Int): Months {
            // returns the month with id as input.
            return when (monthId) {
                JANUARY.id -> JANUARY
                FEBRUARY.id -> FEBRUARY
                MARCH.id -> MARCH
                APRIL.id -> APRIL
                MAY.id -> MAY
                JUNE.id -> JUNE
                JULY.id -> JULY
                AUGUST.id -> AUGUST
                SEPTEMBER.id -> SEPTEMBER
                OCTOBER.id -> OCTOBER
                NOVEMBER.id -> NOVEMBER
                DECEMBER.id -> DECEMBER
                else -> NONE
            }
        }

        fun fromString(month: String): Months {
            // returns the month with stringValue as input.
            return when (month) {
                JANUARY.stringValue -> JANUARY
                FEBRUARY.stringValue -> FEBRUARY
                MARCH.stringValue -> MARCH
                APRIL.stringValue -> APRIL
                MAY.stringValue -> MAY
                JUNE.stringValue -> JUNE
                JULY.stringValue -> JULY
                AUGUST.stringValue -> AUGUST
                SEPTEMBER.stringValue -> SEPTEMBER
                OCTOBER.stringValue -> OCTOBER
                NOVEMBER.stringValue -> NOVEMBER
                DECEMBER.stringValue -> DECEMBER
                else -> NONE
            }
        }
    }
}