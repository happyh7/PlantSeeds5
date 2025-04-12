package com.bps.plantseeds5.modules.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bps.plantseeds5.modules.domain.model.Plant
import java.util.Date

@Entity(
    tableName = "plants",
    foreignKeys = [
        ForeignKey(
            entity = SeedEntity::class,
            parentColumns = ["id"],
            childColumns = ["seedId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("seedId")]
)
data class PlantEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    
    @ColumnInfo(name = "name")
    val name: String,
    
    @ColumnInfo(name = "description")
    val description: String,
    
    @ColumnInfo(name = "seedId")
    val seedId: Long,
    
    @ColumnInfo(name = "plantingDate")
    val plantingDate: Long,
    
    @ColumnInfo(name = "harvestDate")
    val harvestDate: Long? = null,
    
    @ColumnInfo(name = "lastWateredDate")
    val lastWateredDate: Long? = null,
    
    @ColumnInfo(name = "lastFertilizedDate")
    val lastFertilizedDate: Long? = null,
    
    @ColumnInfo(name = "notes")
    val notes: String = "",
    
    @ColumnInfo(name = "imagePath")
    val imagePath: String? = null
) {
    fun validate(): List<String> {
        val errors = mutableListOf<String>()
        
        if (name.isBlank()) {
            errors.add("Plant name cannot be empty")
        }
        
        if (plantingDate <= 0) {
            errors.add("Planting date must be valid")
        }
        
        if (lastWateredDate != null && lastWateredDate <= 0) {
            errors.add("Last watered date must be valid if provided")
        }
        
        if (lastFertilizedDate != null && lastFertilizedDate <= 0) {
            errors.add("Last fertilized date must be valid if provided")
        }
        
        if (lastWateredDate != null && lastWateredDate < plantingDate) {
            errors.add("Last watered date cannot be before planting date")
        }
        
        if (lastFertilizedDate != null && lastFertilizedDate < plantingDate) {
            errors.add("Last fertilized date cannot be before planting date")
        }
        
        return errors
    }

    fun toDomain(): Plant {
        return Plant(
            id = id,
            name = name,
            description = description,
            seedId = seedId,
            plantingDate = Date(plantingDate),
            harvestDate = harvestDate?.let { Date(it) },
            lastWateredDate = lastWateredDate?.let { Date(it) },
            lastFertilizedDate = lastFertilizedDate?.let { Date(it) },
            notes = notes,
            imagePath = imagePath
        )
    }

    companion object {
        fun fromDomain(plant: Plant): PlantEntity {
            return PlantEntity(
                id = plant.id,
                name = plant.name,
                description = plant.description,
                seedId = plant.seedId,
                plantingDate = plant.plantingDate.time,
                harvestDate = plant.harvestDate?.time,
                lastWateredDate = plant.lastWateredDate?.time,
                lastFertilizedDate = plant.lastFertilizedDate?.time,
                notes = plant.notes,
                imagePath = plant.imagePath
            )
        }
    }
} 