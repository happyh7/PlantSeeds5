package com.bps.plantseeds5.modules.data.database.backup

import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.util.Log
import com.bps.plantseeds5.modules.data.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class AutoBackupService : JobService() {
    @Inject
    lateinit var database: AppDatabase

    override fun onStartJob(params: JobParameters?): Boolean {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                performBackup()
                jobFinished(params, false)
            } catch (e: Exception) {
                Log.e("AutoBackupService", "Backup failed", e)
                jobFinished(params, true)
            }
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }

    private fun performBackup() {
        val dbPath = database.openHelper.writableDatabase.path
            ?: throw IllegalStateException("Database path is null")
        val dbFile = File(dbPath)
        val backupDir = File(getExternalFilesDir(null), "backups")
        if (!backupDir.exists()) {
            backupDir.mkdirs()
        }

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val backupFile = File(backupDir, "plantseeds_backup_$timestamp.db")

        FileInputStream(dbFile).use { input ->
            FileOutputStream(backupFile).use { output ->
                input.copyTo(output)
            }
        }
    }
} 