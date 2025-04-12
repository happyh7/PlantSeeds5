package com.bps.plantseeds5.modules.domain.repository

import com.bps.plantseeds5.modules.domain.model.Seed
import kotlinx.coroutines.flow.Flow

interface SeedRepository {
    fun getAllSeeds(): Flow<List<Seed>>
    suspend fun getSeedById(id: Long): Seed?
    suspend fun insertSeed(seed: Seed)
    suspend fun updateSeed(seed: Seed)
    suspend fun deleteSeed(seed: Seed)
    suspend fun getSeedsByPlantingMonth(month: Int): List<Seed>
    fun searchSeeds(query: String): Flow<List<Seed>>
} 