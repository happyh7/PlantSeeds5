package com.bps.plantseeds5.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bps.plantseeds5.data.database.converters.DateConverters

@Database(
    entities = [Seed::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class PlantSeedsDatabase : RoomDatabase() {
    abstract fun seedDao(): SeedDao
} 