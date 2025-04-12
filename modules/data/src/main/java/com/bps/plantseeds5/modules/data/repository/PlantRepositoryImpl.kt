package com.bps.plantseeds5.modules.data.repository

import com.bps.plantseeds5.modules.data.dao.PlantDao
import com.bps.plantseeds5.modules.data.entity.PlantEntity
import com.bps.plantseeds5.modules.domain.model.Plant
import com.bps.plantseeds5.modules.domain.repository.PlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PlantRepositoryImpl @Inject constructor(
    private val plantDao: PlantDao
) : PlantRepository {
    override fun getAllPlants(): Flow<List<Plant>> {
        return plantDao.getAllPlants().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getPlantById(id: Long): Plant? =
        plantDao.getPlantById(id)?.toDomain()

    override suspend fun insertPlant(plant: Plant) {
        plantDao.insertPlant(PlantEntity.fromDomain(plant))
    }

    override suspend fun updatePlant(plant: Plant) {
        plantDao.updatePlant(PlantEntity.fromDomain(plant))
    }

    override suspend fun deletePlant(plant: Plant) {
        plantDao.deletePlant(PlantEntity.fromDomain(plant))
    }

    override fun searchPlants(query: String): Flow<List<Plant>> {
        return plantDao.searchPlants("%$query%").map { entities ->
            entities.map { it.toDomain() }
        }
    }
} 