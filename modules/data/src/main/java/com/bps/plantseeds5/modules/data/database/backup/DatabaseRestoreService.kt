package com.bps.plantseeds5.modules.data.database.backup

import android.content.Context
import android.util.Log
import com.bps.plantseeds5.modules.data.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class DatabaseRestoreService @Inject constructor(
    private val context: Context,
    private val database: AppDatabase
) {
    private val backupDir = File(context.getExternalFilesDir(null), "backups")

    fun listAvailableBackups(): List<File> {
        return backupDir.listFiles { file -> 
            file.isFile && file.name.startsWith("plantseeds_backup_") && file.name.endsWith(".db")
        }?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    fun restoreFromBackup(backupFile: File, onComplete: (Boolean, String?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                validateBackupFile(backupFile)
                
                // Close the current database
                database.close()
                
                val dbPath = database.openHelper.writableDatabase.path
                    ?: throw IllegalStateException("Database path is null")
                val dbFile = File(dbPath)
                
                // Create a backup of the current database before restoring
                val timestamp = System.currentTimeMillis()
                val currentBackup = File(backupDir, "pre_restore_backup_$timestamp.db")
                FileInputStream(dbFile).use { input ->
                    FileOutputStream(currentBackup).use { output ->
                        input.copyTo(output)
                    }
                }
                
                // Restore from backup
                FileInputStream(backupFile).use { input ->
                    FileOutputStream(dbFile).use { output ->
                        input.copyTo(output)
                    }
                }
                
                // Reopen the database
                database.openHelper.writableDatabase
                
                onComplete(true, null)
            } catch (e: Exception) {
                Log.e("DatabaseRestoreService", "Restore failed", e)
                onComplete(false, e.message)
            }
        }
    }

    private fun validateBackupFile(backupFile: File) {
        if (!backupFile.exists()) {
            throw IllegalArgumentException("Backup file does not exist")
        }
        if (!backupFile.canRead()) {
            throw IllegalArgumentException("Backup file is not readable")
        }
        if (backupFile.length() == 0L) {
            throw IllegalArgumentException("Backup file is empty")
        }
        // Add more validation as needed
    }
} 