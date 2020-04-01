package com.gcuestab.pushnotification.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.gcuestab.pushnotification.*
import com.gcuestab.pushnotification.R
import java.util.concurrent.TimeUnit

object MyNotificationManager {
    fun makeNotification(type: String, message: String, context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(context)
        }

        val builder = NotificationCompat.Builder(
            context,
            NOTIFICATION_CHANNEL_ID
        )
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(type)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVibrate(LongArray(0))

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context) {
        val name = NOTIFICATION_CHANNEL_NAME
        val description = NOTIFICATION_CHANNEL_DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_HIGH

        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, importance)
        channel.description = description

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        notificationManager?.createNotificationChannel(channel)
    }

    fun saveNotification(application: Application, type: String?, description: String?) {
        val workManager = WorkManager.getInstance(application)

        val builder = Data.Builder()
        builder.putString(NOTIFICATION_WORK_TITLE_KEY, type)
        builder.putString(NOTIFICATION_WORK_DESCRIPTION_KEY, description)

        val notificationBuilder = OneTimeWorkRequestBuilder<SaveNotificationWorker>()
        notificationBuilder.setInputData(builder.build())
        notificationBuilder.setBackoffCriteria(
            BackoffPolicy.LINEAR,
            OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
            TimeUnit.MILLISECONDS
        )

        workManager
            .beginUniqueWork(
                NOTIFICATION_WORK_NAME,
                ExistingWorkPolicy.APPEND,
                notificationBuilder.build()
            )
            .enqueue()
    }
}