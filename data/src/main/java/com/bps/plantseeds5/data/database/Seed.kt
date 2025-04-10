package com.bps.plantseeds5.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seeds")
data class Seed(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val sowingTime: String,
    val harvestTime: String,
    val createdAt: Long = System.currentTimeMillis()
) 