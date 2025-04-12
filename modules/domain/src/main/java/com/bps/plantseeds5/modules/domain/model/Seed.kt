package com.bps.plantseeds5.modules.domain.model

data class Seed(
    val id: Long = 0,
    val name: String,
    val description: String,
    val plantingMonths: List<Int>
) 