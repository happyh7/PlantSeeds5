package com.bps.plantseeds5.modules.data.database.backup

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.bps.plantseeds5.modules.data.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class DatabaseRestoreService : Service() {

    @Inject
    lateinit var database: AppDatabase

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "DatabaseRestoreService created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "DatabaseRestoreService started")
        
        val backupPath = intent?.getStringExtra(EXTRA_BACKUP_PATH)
        if (backupPath == null) {
            Log.e(TAG, "No backup path provided")
            stopSelf()
            return START_NOT_STICKY
        }

        performRestore(backupPath)
        return START_NOT_STICKY
    }

    private fun performRestore(backupPath: String) {
        Log.d(TAG, "Starting database restore from: $backupPath")
        try {
            val backupFile = File(backupPath)
            if (!backupFile.exists()) {
                Log.e(TAG, "Backup file does not exist at path: $backupPath")
                return
            }

            val dbPath = database.openHelper.writableDatabase.path
                ?: throw IllegalStateException("Database path is null")
            val dbFile = File(dbPath)

            database.close()
            backupFile.copyTo(dbFile, overwrite = true)
            Log.d(TAG, "Database restore completed successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to restore database", e)
        }
    }

    companion object {
        private const val TAG = "DatabaseRestoreService"
        const val EXTRA_BACKUP_PATH = "backup_path"
    }
} 