package com.bps.plantseeds5.modules.data.database.backup

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.bps.plantseeds5.modules.data.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AutoBackupService : Service() {

    @Inject
    lateinit var database: AppDatabase

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "AutoBackupService created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "AutoBackupService started")
        performBackup()
        return START_NOT_STICKY
    }

    private fun performBackup() {
        Log.d(TAG, "Starting database backup")
        try {
            val dbPath = database.openHelper.writableDatabase.path
                ?: throw IllegalStateException("Database path is null")
            val dbFile = File(dbPath)
            
            val backupDir = File(applicationContext.filesDir, "backups")
            if (!backupDir.exists()) {
                backupDir.mkdirs()
            }

            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val backupFile = File(backupDir, "plantseeds_backup_$timestamp.db")

            dbFile.copyTo(backupFile, overwrite = true)
            Log.d(TAG, "Database backup completed successfully: ${backupFile.absolutePath}")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to backup database", e)
        }
    }

    companion object {
        private const val TAG = "AutoBackupService"
    }
} 