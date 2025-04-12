package com.bps.plantseeds5.modules.domain.repository

import com.bps.plantseeds5.modules.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    fun getAllPlants(): Flow<List<Plant>>
    suspend fun getPlantById(id: Long): Plant?
    suspend fun insertPlant(plant: Plant)
    suspend fun updatePlant(plant: Plant)
    suspend fun deletePlant(plant: Plant)
    fun searchPlants(query: String): Flow<List<Plant>>
} 