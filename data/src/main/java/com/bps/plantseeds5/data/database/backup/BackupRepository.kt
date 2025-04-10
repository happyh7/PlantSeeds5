package com.bps.plantseeds5.data.database.backup

import android.content.Context
import android.os.Build
import com.bps.plantseeds5.data.database.PlantSeedsDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

class BackupRepository(
    private val context: Context,
    private val database: PlantSeedsDatabase
) {
    private val backupManager = DatabaseBackupManager(context, database)

    suspend fun createBackup(): Flow<Result<String>> = flow {
        try {
            val backupPath = backupManager.createBackup()
            emit(Result.success(backupPath))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    suspend fun restoreFromBackup(backupFilePath: String): Flow<Result<Boolean>> = flow {
        try {
            val success = backupManager.restoreFromBackup(backupFilePath)
            emit(Result.success(success))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getAvailableBackups(): List<File> {
        return backupManager.getAvailableBackups()
    }

    fun deleteBackup(backupFilePath: String): Boolean {
        return backupManager.deleteBackup(backupFilePath)
    }

    fun getBackupDir(): File {
        return backupManager.getBackupDir()
    }

    fun startAutoBackup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AutoBackupService.schedule(context)
        }
    }

    fun stopAutoBackup() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AutoBackupService.cancel(context)
        }
    }
} 