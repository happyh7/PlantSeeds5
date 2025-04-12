package com.bps.plantseeds5.modules.domain.model

data class Plant(
    val id: Long = 0,
    val name: String,
    val description: String,
    val seedId: Long,
    val plantingDate: Long,
    val harvestDate: Long? = null,
    val notes: String = ""
) 