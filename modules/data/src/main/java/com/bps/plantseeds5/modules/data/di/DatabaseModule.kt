package com.bps.plantseeds5.modules.data.di

import android.content.Context
import androidx.room.Room
import com.bps.plantseeds5.modules.data.dao.PlantDao
import com.bps.plantseeds5.modules.data.dao.SeedDao
import com.bps.plantseeds5.modules.data.database.AppDatabase
import com.bps.plantseeds5.modules.data.database.migrations.Migration3To4
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
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "plant_seeds.db"
    )
    .addMigrations(Migration3To4())
    .fallbackToDestructiveMigration()
    .build()

    @Provides
    @Singleton
    fun provideSeedDao(database: AppDatabase): SeedDao = database.seedDao()

    @Provides
    @Singleton
    fun providePlantDao(database: AppDatabase): PlantDao = database.plantDao()
} 