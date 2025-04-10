package com.bps.plantseeds5.data.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    // Exempel på en migration från version 1 till 2
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Här kan vi lägga till nya kolumner eller ändra befintliga
            db.execSQL("""
                ALTER TABLE seeds 
                ADD COLUMN variety TEXT
            """)
        }
    }

    // Exempel på en migration från version 2 till 3
    val MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Här kan vi skapa en ny tabell
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS seed_varieties (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    seed_id INTEGER NOT NULL,
                    name TEXT NOT NULL,
                    description TEXT,
                    FOREIGN KEY(seed_id) REFERENCES seeds(id) ON DELETE CASCADE
                )
            """)
        }
    }

    // Exempel på en migration från version 3 till 4
    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(db: SupportSQLiteDatabase) {
            // Här kan vi ändra en kolumns datatyp
            db.execSQL("""
                CREATE TABLE IF NOT EXISTS seeds_new (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    name TEXT NOT NULL,
                    description TEXT NOT NULL,
                    sowing_time INTEGER NOT NULL,
                    harvest_time INTEGER NOT NULL,
                    created_at INTEGER NOT NULL,
                    variety TEXT,
                    difficulty_level INTEGER NOT NULL DEFAULT 1
                )
            """)
            
            db.execSQL("""
                INSERT INTO seeds_new (id, name, description, sowing_time, harvest_time, created_at, variety)
                SELECT id, name, description, sowingTime, harvestTime, createdAt, variety
                FROM seeds
            """)
            
            db.execSQL("DROP TABLE seeds")
            db.execSQL("ALTER TABLE seeds_new RENAME TO seeds")
        }
    }
} 