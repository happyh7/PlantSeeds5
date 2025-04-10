package com.bps.plantseeds5.data.database.backup

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.bps.plantseeds5.data.database.PlantSeedsDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class AutoBackupService : JobService() {
    private var job: Job? = null

    override fun onStartJob(params: JobParameters?): Boolean {
        job = CoroutineScope(Dispatchers.IO).launch {
            val database = PlantSeedsDatabase.getDatabase(applicationContext)
            val backupManager = DatabaseBackupManager(applicationContext, database)
            
            try {
                backupManager.createBackup()
                jobFinished(params, false)
            } catch (e: Exception) {
                e.printStackTrace()
                jobFinished(params, true)
            }
        }
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        job?.cancel()
        return true
    }

    companion object {
        private const val JOB_ID = 1
        private const val BACKUP_INTERVAL_HOURS = 24L

        fun schedule(context: Context) {
            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            
            val jobInfo = JobInfo.Builder(JOB_ID, ComponentName(context, AutoBackupService::class.java))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE)
                .setPeriodic(TimeUnit.HOURS.toMillis(BACKUP_INTERVAL_HOURS))
                .setPersisted(true)
                .build()

            jobScheduler.schedule(jobInfo)
        }

        fun cancel(context: Context) {
            val jobScheduler = context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            jobScheduler.cancel(JOB_ID)
        }
    }
} 