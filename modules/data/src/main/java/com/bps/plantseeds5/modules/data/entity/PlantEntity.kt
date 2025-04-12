package com.bps.plantseeds5.modules.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bps.plantseeds5.modules.domain.model.Plant

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
    val harvestDate: Long?,
    
    @ColumnInfo(name = "notes")
    val notes: String
) {
    @Ignore
    fun toDomain() = Plant(
        id = id,
        name = name,
        description = description,
        seedId = seedId,
        plantingDate = plantingDate,
        harvestDate = harvestDate,
        notes = notes
    )

    companion object {
        @Ignore
        fun fromDomain(plant: Plant) = PlantEntity(
            id = plant.id,
            name = plant.name,
            description = plant.description,
            seedId = plant.seedId,
            plantingDate = plant.plantingDate,
            harvestDate = plant.harvestDate,
            notes = plant.notes
        )
    }
} 