package com.bps.plantseeds5.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "seeds")
data class Seed(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val sowingTime: Date,
    val harvestTime: Date,
    val createdAt: Date = Date(),
    val variety: String? = null,
    val difficultyLevel: Int = 1
) 