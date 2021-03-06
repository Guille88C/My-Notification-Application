package com.gcuestab.pushnotification.notification

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.gcuestab.pushnotification.MODULE_TAG
import com.gcuestab.pushnotification.NOTIFICATION_WORK_DESCRIPTION_KEY
import com.gcuestab.pushnotification.NOTIFICATION_WORK_TITLE_KEY
import com.gcuestab.pushnotification.data.NotificationData
import com.gcuestab.pushnotification.di.getNotificationRepository

internal class SaveNotificationWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {

    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        return try {
            insertNotification(title = getTitle(), description = getDescription())
            Result.success()
        } catch (throwable: Throwable) {
            Log.e(MODULE_TAG, "Error inserting notification")
            Result.retry()
        }
    }

    private fun getTitle(): String {
        val type = inputData.getString(NOTIFICATION_WORK_TITLE_KEY)
        if (type.isNullOrEmpty()) {
            Log.e(MODULE_TAG, "Invalid input type")
            throw IllegalArgumentException("Invalid input type")
        }
        return type
    }

    private fun getDescription(): String {
        val description = inputData.getString(NOTIFICATION_WORK_DESCRIPTION_KEY)
        if (description.isNullOrEmpty()) {
            Log.e(MODULE_TAG, "Invalid input description")
            throw IllegalArgumentException("Invalid input description")
        }
        return description
    }

    private fun insertNotification(
        title: String,
        description: String
    ) {
        getNotificationRepository(context = applicationContext).insertNotification(
            notification = NotificationData(
                id = 0,
                isRead = false,
                date = System.currentTimeMillis(),
                title = title,
                description = description
            )
        )
    }
}
