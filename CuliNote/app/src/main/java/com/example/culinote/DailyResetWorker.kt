package com.example.culinote

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class DailyResetWorker(context: Context, params: WorkerParameters): Worker(context, params){
    override fun doWork(): Result {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(applicationContext, "daily_reset_channel")
            //.setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("CuliNote Reset")
            .setContentText("Your daily limit has been reset. You can now search for new recipes!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(2, builder.build())
        return Result.success()
    }
}