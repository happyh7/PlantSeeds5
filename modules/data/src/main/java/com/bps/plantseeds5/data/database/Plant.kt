package com.bps.plantseeds5.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val seedId: Long, // Referens till fröet växten kommer från
    val plantingDate: Date,
    val lastWateringDate: Date? = null,
    val lastFertilizingDate: Date? = null,
    val location: String? = null,
    val notes: String? = null,
    val status: PlantStatus = PlantStatus.ACTIVE,
    val createdDate: Date = Date(),
    val updatedDate: Date = Date()
)

enum class PlantStatus {
    ACTIVE,
    HARVESTED,
    REMOVED
} 