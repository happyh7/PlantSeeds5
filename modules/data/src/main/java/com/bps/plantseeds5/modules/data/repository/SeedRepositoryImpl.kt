package com.bps.plantseeds5.modules.data.repository

import com.bps.plantseeds5.modules.data.dao.SeedDao
import com.bps.plantseeds5.modules.data.entity.SeedEntity
import com.bps.plantseeds5.modules.domain.model.Seed
import com.bps.plantseeds5.modules.domain.repository.SeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SeedRepositoryImpl @Inject constructor(
    private val seedDao: SeedDao
) : SeedRepository {
    override fun getAllSeeds(): Flow<List<Seed>> {
        return seedDao.getAllSeeds().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getSeedById(id: Long): Seed? =
        seedDao.getSeedById(id)?.toDomain()

    override suspend fun insertSeed(seed: Seed) {
        seedDao.insertSeed(SeedEntity.fromDomain(seed))
    }

    override suspend fun updateSeed(seed: Seed) {
        seedDao.updateSeed(SeedEntity.fromDomain(seed))
    }

    override suspend fun deleteSeed(seed: Seed) {
        seedDao.deleteSeed(SeedEntity.fromDomain(seed))
    }

    override suspend fun getSeedsByPlantingMonth(month: Int): List<Seed> =
        seedDao.getSeedsByPlantingMonth(month).map { it.toDomain() }

    override fun searchSeeds(query: String): Flow<List<Seed>> {
        return seedDao.searchSeeds("%$query%").map { entities ->
            entities.map { it.toDomain() }
        }
    }
}

private fun SeedEntity.toDomain(): Seed {
    return Seed(
        id = id,
        name = name,
        description = description,
        plantingMonths = plantingMonths
    )
}

private fun SeedEntity.Companion.fromDomain(seed: Seed): SeedEntity {
    return SeedEntity(
        id = seed.id,
        name = seed.name,
        description = seed.description,
        plantingMonths = seed.plantingMonths
    )
}