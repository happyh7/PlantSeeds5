package com.bps.plantseeds5.modules.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bps.plantseeds5.modules.data.converter.IntListConverter
import com.bps.plantseeds5.modules.domain.model.Seed

@Entity(tableName = "seeds")
@TypeConverters(IntListConverter::class)
data class SeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val plantingMonths: List<Int>
) {
    fun toDomain() = Seed(
        id = id,
        name = name,
        description = description,
        plantingMonths = plantingMonths
    )

    companion object {
        fun fromDomain(seed: Seed) = SeedEntity(
            id = seed.id,
            name = seed.name,
            description = seed.description,
            plantingMonths = seed.plantingMonths
        )
    }
} 