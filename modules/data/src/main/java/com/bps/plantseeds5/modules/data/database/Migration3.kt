package com.bps.plantseeds5.modules.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Drop the existing table
        database.execSQL("DROP TABLE IF EXISTS plants")
        
        // Create the table with all required columns
        database.execSQL("""
            CREATE TABLE IF NOT EXISTS plants (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                name TEXT NOT NULL,
                description TEXT NOT NULL,
                seedId INTEGER NOT NULL,
                plantingDate INTEGER NOT NULL,
                harvestDate INTEGER,
                notes TEXT,
                FOREIGN KEY (seedId) REFERENCES seeds(id) ON DELETE CASCADE
            )
        """)
        
        // Create the index
        database.execSQL("CREATE INDEX IF NOT EXISTS index_plants_seedId ON plants(seedId)")
    }
} 