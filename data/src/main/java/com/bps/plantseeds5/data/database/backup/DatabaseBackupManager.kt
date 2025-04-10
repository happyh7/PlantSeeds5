package com.bps.plantseeds5.data.database.backup

import android.content.Context
import android.os.Build
import androidx.room.RoomDatabase
import com.bps.plantseeds5.data.database.PlantSeedsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DatabaseBackupManager(
    private val context: Context,
    private val database: PlantSeedsDatabase
) {
    private val backupDir = File(context.filesDir, "database_backups")
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault())

    init {
        if (!backupDir.exists()) {
            backupDir.mkdirs()
        }
    }

    suspend fun createBackup(): String = withContext(Dispatchers.IO) {
        val timestamp = dateFormat.format(Date())
        val backupFileName = "plantseeds_backup_$timestamp.db"
        val backupFile = File(backupDir, backupFileName)

        // Stäng databasen för att säkerställa att alla ändringar är skrivna
        database.close()

        // Kopiera databasfilen
        val dbFile = context.getDatabasePath(database.openHelper.writableDatabase.path)
        FileInputStream(dbFile).use { input ->
            FileOutputStream(backupFile).use { output ->
                input.copyTo(output)
            }
        }

        // Öppna databasen igen
        database.openHelper.writableDatabase

        backupFile.absolutePath
    }

    suspend fun restoreFromBackup(backupFilePath: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val backupFile = File(backupFilePath)
            if (!backupFile.exists()) {
                return@withContext false
            }

            // Stäng databasen
            database.close()

            // Kopiera backup-filen till databasfilen
            val dbFile = context.getDatabasePath(database.openHelper.writableDatabase.path)
            FileInputStream(backupFile).use { input ->
                FileOutputStream(dbFile).use { output ->
                    input.copyTo(output)
                }
            }

            // Öppna databasen igen
            database.openHelper.writableDatabase

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getAvailableBackups(): List<File> {
        return backupDir.listFiles()?.filter { it.name.endsWith(".db") }?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    fun deleteBackup(backupFilePath: String): Boolean {
        val backupFile = File(backupFilePath)
        return backupFile.delete()
    }

    fun getBackupDir(): File {
        return backupDir
    }
} 