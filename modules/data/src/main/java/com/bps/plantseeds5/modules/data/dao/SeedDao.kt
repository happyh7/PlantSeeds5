package com.bps.plantseeds5.modules.data.dao

import androidx.room.*
import com.bps.plantseeds5.modules.data.entity.SeedEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeedDao {
    @Query("SELECT * FROM seeds")
    fun getAllSeeds(): Flow<List<SeedEntity>>

    @Query("SELECT * FROM seeds WHERE id = :id")
    suspend fun getSeedById(id: Long): SeedEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeed(seed: SeedEntity)

    @Update
    suspend fun updateSeed(seed: SeedEntity)

    @Delete
    suspend fun deleteSeed(seed: SeedEntity)

    @Query("SELECT * FROM seeds WHERE :month IN (SELECT value FROM json_each(plantingMonths))")
    suspend fun getSeedsByPlantingMonth(month: Int): List<SeedEntity>

    @Query("SELECT * FROM seeds WHERE name LIKE :query OR description LIKE :query")
    fun searchSeeds(query: String): Flow<List<SeedEntity>>
} 