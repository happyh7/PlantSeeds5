package com.bps.plantseeds5.modules.data.di

import com.bps.plantseeds5.modules.data.repository.PlantRepositoryImpl
import com.bps.plantseeds5.modules.data.repository.SeedRepositoryImpl
import com.bps.plantseeds5.modules.domain.repository.PlantRepository
import com.bps.plantseeds5.modules.domain.repository.SeedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSeedRepository(
        seedRepositoryImpl: SeedRepositoryImpl
    ): SeedRepository

    @Binds
    @Singleton
    abstract fun bindPlantRepository(
        plantRepositoryImpl: PlantRepositoryImpl
    ): PlantRepository
} 