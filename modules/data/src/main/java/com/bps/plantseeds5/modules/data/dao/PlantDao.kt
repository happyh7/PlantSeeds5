package com.bps.plantseeds5.modules.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bps.plantseeds5.modules.data.entity.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun getAllPlants(): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE id = :id")
    suspend fun getPlantById(id: Long): PlantEntity?

    @Query("SELECT * FROM plants WHERE seedId = :seedId")
    fun getPlantsBySeedId(seedId: Long): Flow<List<PlantEntity>>

    @Query("SELECT * FROM plants WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun searchPlants(query: String): Flow<List<PlantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: PlantEntity): Long {
        val errors = plant.validate()
        if (errors.isNotEmpty()) {
            throw IllegalArgumentException("Invalid plant data: ${errors.joinToString(", ")}")
        }
        return insert(plant)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: PlantEntity): Long

    @Update
    suspend fun updatePlant(plant: PlantEntity) {
        val errors = plant.validate()
        if (errors.isNotEmpty()) {
            throw IllegalArgumentException("Invalid plant data: ${errors.joinToString(", ")}")
        }
        update(plant)
    }

    @Update
    suspend fun update(plant: PlantEntity)

    @Delete
    suspend fun deletePlant(plant: PlantEntity)

    @Query("DELETE FROM plants WHERE seedId = :seedId")
    suspend fun deletePlantsBySeedId(seedId: Long)
} 