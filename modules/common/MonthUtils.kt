package com.bps.plantseeds5.modules.common

object MonthUtils {
    fun getMonthName(month: Int): String {
        return when (month) {
            1 -> "Januari"
            2 -> "Februari"
            3 -> "Mars"
            4 -> "April"
            5 -> "Maj"
            6 -> "Juni"
            7 -> "Juli"
            8 -> "Augusti"
            9 -> "September"
            10 -> "Oktober"
            11 -> "November"
            12 -> "December"
            else -> "Okänd månad"
        }
    }
} 