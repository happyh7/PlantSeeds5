package com.bps.plantseeds5.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.bps.plantseeds5.data.database.converters.DateConverters

@Database(
    entities = [Seed::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverters::class)
abstract class PlantSeedsDatabase : RoomDatabase() {
    abstract fun seedDao(): SeedDao

    companion object {
        // Här definierar vi våra migrations
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    ALTER TABLE seeds 
                    ADD COLUMN variety TEXT
                """)
            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
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

        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
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
} 