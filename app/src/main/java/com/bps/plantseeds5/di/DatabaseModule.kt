package com.bps.plantseeds5.di

import android.content.Context
import androidx.room.Room
import com.bps.plantseeds5.data.PlantDao
import com.bps.plantseeds5.data.PlantSeedsDatabase
import com.bps.plantseeds5.data.SeedDao
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
        return Room.databaseBuilder(
            context,
            PlantSeedsDatabase::class.java,
            "plantseeds.db"
        ).build()
    }

    @Provides
    fun provideSeedDao(database: PlantSeedsDatabase): SeedDao {
        return database.seedDao()
    }

    @Provides
    fun providePlantDao(database: PlantSeedsDatabase): PlantDao {
        return database.plantDao()
    }
} 