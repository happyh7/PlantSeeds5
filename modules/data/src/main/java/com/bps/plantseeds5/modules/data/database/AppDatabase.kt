package com.bps.plantseeds5.modules.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bps.plantseeds5.modules.data.dao.PlantDao
import com.bps.plantseeds5.modules.data.dao.SeedDao
import com.bps.plantseeds5.modules.data.entity.PlantEntity
import com.bps.plantseeds5.modules.data.entity.SeedEntity
import com.bps.plantseeds5.modules.data.converter.IntListConverter
import com.bps.plantseeds5.modules.data.database.migrations.Migration3To4

@Database(
    entities = [
        SeedEntity::class,
        PlantEntity::class
    ],
    version = 4,
    exportSchema = true,
    autoMigrations = []
)
@TypeConverters(IntListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun seedDao(): SeedDao
    abstract fun plantDao(): PlantDao

    companion object {
        val MIGRATION_3_4 = Migration3To4()
    }
} 