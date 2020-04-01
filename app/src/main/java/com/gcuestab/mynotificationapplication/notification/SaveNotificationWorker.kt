package com.gcuestab.mynotificationapplication.notification

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.gcuestab.mynotificationapplication.*
import com.gcuestab.mynotificationapplication.data.NotificationData
import com.gcuestab.mynotificationapplication.data.NotificationLocalDataSourceImpl
import com.gcuestab.mynotificationapplication.data.NotificationRepository
import com.gcuestab.mynotificationapplication.data.database.NotificationDataBaseRoom

internal class SaveNotificationWorker(context: Context, params: WorkerParameters) :
    Worker(context, params) {
    @SuppressLint("RestrictedApi")
    override fun doWork(): Result {
        return try {
            insertNotification(type = getType(), description = getDescription())
            Result.Success()
        } catch (throwable: Throwable) {
            Log.e(APP_TAG, "Error inserting notification")
            Result.failure()
        }
    }

    private fun getType(): String {
        val type = inputData.getString(NOTIFICATION_WORK_TYPE_KEY)
        if (type.isNullOrEmpty()) {
            Log.e(APP_TAG, "Invalid input type")
            throw IllegalArgumentException("Invalid input type")
        }
        return type
    }

    private fun getDescription(): String {
        val description = inputData.getString(NOTIFICATION_WORK_DESCRIPTION_KEY)
        if (description.isNullOrEmpty()) {
            Log.e(APP_TAG, "Invalid input description")
            throw IllegalArgumentException("Invalid input description")
        }
        return description
    }

    private fun insertNotification(
        type: String,
        description: String
    ) {
        createRepository().insertNotification(
            notification = NotificationData(
                id = 0,
                isRead = false,
                date = System.currentTimeMillis(),
                type = when (type) {
                    NOTIFICATION_DELIVERY_VALUE -> NotificationData.Type.DELIVERY
                    NOTIFICATION_APPOINTMENT_VALE -> NotificationData.Type.APPOINTMENT
                    else -> NotificationData.Type.UNKNOWN
                },
                description = description
            )
        )
    }

    private fun createRepository(): NotificationRepository {
        return NotificationRepository(
            localDataSource = NotificationLocalDataSourceImpl(
                database = NotificationDataBaseRoom.getDataBase(context = applicationContext)
            )
        )
    }
}
