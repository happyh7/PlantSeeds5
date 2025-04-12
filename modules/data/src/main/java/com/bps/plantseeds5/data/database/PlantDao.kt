package com.bps.plantseeds5.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants ORDER BY plantingDate DESC")
    fun getAllPlants(): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE id = :id")
    suspend fun getPlantById(id: Long): Plant?

    @Query("SELECT * FROM plants WHERE seedId = :seedId ORDER BY plantingDate DESC")
    fun getPlantsBySeedId(seedId: Long): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE status = :status ORDER BY plantingDate DESC")
    fun getPlantsByStatus(status: PlantStatus): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE lastWateringDate < :date OR lastWateringDate IS NULL")
    fun getPlantsNeedingWatering(date: Date): Flow<List<Plant>>

    @Query("SELECT * FROM plants WHERE lastFertilizingDate < :date OR lastFertilizingDate IS NULL")
    fun getPlantsNeedingFertilizing(date: Date): Flow<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlant(plant: Plant): Long

    @Update
    suspend fun updatePlant(plant: Plant)

    @Delete
    suspend fun deletePlant(plant: Plant)

    @Query("UPDATE plants SET lastWateringDate = :date WHERE id = :plantId")
    suspend fun updateLastWateringDate(plantId: Long, date: Date)

    @Query("UPDATE plants SET lastFertilizingDate = :date WHERE id = :plantId")
    suspend fun updateLastFertilizingDate(plantId: Long, date: Date)

    @Query("UPDATE plants SET status = :status, updatedDate = :date WHERE id = :plantId")
    suspend fun updatePlantStatus(plantId: Long, status: PlantStatus, date: Date = Date())
} 