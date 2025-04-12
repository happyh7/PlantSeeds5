package com.bps.plantseeds5.modules.domain.model

import java.util.Date

data class Plant(
    val id: Long = 0,
    val name: String,
    val description: String,
    val seedId: Long,
    val plantingDate: Date,
    val harvestDate: Date? = null,
    val lastWateredDate: Date? = null,
    val lastFertilizedDate: Date? = null,
    val notes: String = "",
    val imagePath: String? = null
) 