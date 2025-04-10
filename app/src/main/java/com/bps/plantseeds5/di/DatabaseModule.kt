package com.bps.plantseeds5.di

import android.content.Context
import com.bps.plantseeds5.data.database.PlantSeedsDatabase
import com.bps.plantseeds5.data.database.SeedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PlantSeedsDatabase {
        return PlantSeedsDatabase.getDatabase(context)
    }

    @Provides
    fun provideSeedDao(database: PlantSeedsDatabase): SeedDao {
        return database.seedDao()
    }
} 