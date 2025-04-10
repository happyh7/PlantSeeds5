package com.bps.plantseeds5.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Seed::class],
    version = 1,
    exportSchema = false
)
abstract class PlantSeedsDatabase : RoomDatabase() {
    abstract fun seedDao(): SeedDao
} 