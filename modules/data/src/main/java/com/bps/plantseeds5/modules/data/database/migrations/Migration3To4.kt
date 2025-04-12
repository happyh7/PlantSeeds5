package com.bps.plantseeds5.modules.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration3To4 : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add new columns to plants table
        database.execSQL("ALTER TABLE plants ADD COLUMN plantingDate INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE plants ADD COLUMN harvestDate INTEGER")
        database.execSQL("ALTER TABLE plants ADD COLUMN notes TEXT NOT NULL DEFAULT ''")
    }
} 