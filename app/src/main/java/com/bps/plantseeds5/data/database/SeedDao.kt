package com.bps.plantseeds5.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SeedDao {
    @Query("SELECT * FROM seeds ORDER BY name ASC")
    fun getAllSeeds(): Flow<List<Seed>>

    @Query("SELECT * FROM seeds WHERE id = :id")
    suspend fun getSeedById(id: Long): Seed?

    @Insert
    suspend fun insertSeed(seed: Seed): Long

    @Update
    suspend fun updateSeed(seed: Seed)

    @Delete
    suspend fun deleteSeed(seed: Seed)
} 